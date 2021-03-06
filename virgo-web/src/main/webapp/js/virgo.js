$(function(){
    $.ajaxSetup({
        cache:false,
        complete:function(jqXHR,textStatus){
            if(jqXHR.getResponseHeader('sessionstatus')=='timeout'){
                window.location.href='/';
            }
        }
    });
});

function initIndex(){
    $('header').find('li').removeClass('active');
    $('header').find('li:eq(0)').addClass('active');
    $('#sign-btn').click(signup);
    $('#login-form').submit(login);
    $('#regusername').blur(validateUsername);
    $('#regpassword').blur(validatePassword);
}

function initUserIndex(){
    $('header').find('li').removeClass('active');
    $('header').find('li:eq(3)').addClass('active');
    $('#edit').click(editProfile);
    $('#modify').click(modifyPassword);
}

function initBillIndex(){
    $('#chart').css({"width":$('#table').width()+"px"});
    var width = $('#table').width();
    if(width < 800){
        $('#incomePie,#payPie').css({"width":width+"px"});
    } else {
        $('#incomePie,#payPie').css({"width":width/2+"px"});
    }
    $('header').find('li').removeClass('active');
    $('header').find('li:eq(1)').addClass('active');
    queryBill();
    $('#month').change(queryBill);
    $('#year').change(queryBill);
    decideShowType();
    $('input[name="style"]').change(decideShowType);
}

function initBillAdd(){
    $('header').find('li').removeClass('active');
    $('header').find('li:eq(2)').addClass('active');
    $('#addBill').click(addBill);
    $('.datepicker').datepicker();
}

//首页
function validateUsername(event, uncheck){
    var result = true;
    var username = $('#regusername').val();
    if(!username || username.length < 4){
        $('#sign-username-fg').removeClass('has-success').addClass('has-error');
        $('#sign-username-error').text('用户名不能少于4个字符');
        result = false;
    } else if(username.length > 20){
        $('#sign-username-fg').removeClass('has-success').addClass('has-error');
        $('#sign-username-error').text('用户名不能多于20个字符');
        result = false;
    } else {
        if(!uncheck){
            $('#sign-username-fg').removeClass('has-error');
            $('#sign-username-error').text('正在检测用户名');
            $.post('/signup/'+username, function(data){
                if(data){
                    $('#sign-username-fg').addClass('has-error');
                    $('#sign-username-error').text('该用户名已存在');
                } else {
                    $('#sign-username-fg').addClass('has-success');
                    $('#sign-username-error').text('');
                }
            });
        } else {
            $('#sign-username-fg').removeClass('has-error').addClass('has-success');
            $('#sign-username-error').text('');
        }
    }
    return result;
}

function validatePassword(){
    var result = true;
    var password = $('#regpassword').val();
    if(!password || password.length < 6){
        $('#sign-password-fg').removeClass('has-success').addClass('has-error');
        $('#sign-password-error').text('密码不能少于6个字符');
        result = false;
    } else if(password.length > 20){
        $('#sign-password-fg').removeClass('has-success').addClass('has-error');
        $('#sign-password-error').text('密码不能多于20个字符');
        result = false;
    } else {
        $('#sign-password-fg').removeClass('has-error').addClass('has-success');
        $('#sign-password-error').text('');
    }
    return result;
}

function signup(){
    var username = $('#regusername').val();
    var password = $('#regpassword').val();
    if(validateUsername(true, true) && validatePassword()){
        $.post('/signup', $( "#signup-form" ).serialize(),function(data){
            if(data == 0){
                $('#signup-form').submit();
            } else if(data == 2){
                $('#sign-username-fg').addClass('has-error');
                $('#sign-username-error').text('该用户名已存在');
            }
        });
    }
    return false;
}

function login(){
    $.post('/login', $('#login-form').serialize(),function(data){
        if(data==0){
            window.location.href='/bill'
        } else {
            $('#login-error').text('用户名或者密码错误');
        }
    });
    return false;
}

//添加账单
function addBill(){
    $('#addBill').unbind('click');
    if(stringLength($('#item'), 1, 255) && isNumber($('#money')) && stringLength($('#remark'), 0, 255)){
        $.post('add', $('#bill-form').serialize(), function(data){
            if(data){
                $('#money').val('');
                $('#item').val('');
                $('#remark').val('');
                $('#add-tip').text('保存成功');
            } else {
                $('#add-tip').text('保存失败');
            }
            setTimeout(function(){$('#add-tip').text('');},3000);
            $('#addBill').click(addBill);
        });
    } else {
        $('#addBill').click(addBill);
    }
}

function isNumber(input){
    var number = $.trim(input.val());
    var pattern = /^\d+(.\d{1,2})?$/;
    if(pattern.test(number)){
        input.parent().removeClass('has-error');
        return true;
    }
    input.parent().addClass('has-error');
    return false;
}

function stringLength(input, min, max){
    var text = $.trim(input.val());
    if(text.length>=min && text.length<=max){
        input.parent().removeClass('has-error');
        return true;
    }
    input.parent().addClass('has-error');
    return false;
}

//查询账单
function queryBill(){
    var year = parseInt($('#year').val());
    var month = parseInt($('#month').val());
    var params = '';
    if(year == -1){
        $('#month').val(-1);
    }
    if(year != -1 && month == -1) {
        var from = year+'-01-01';
        var to = year+'-12-31';
        params = 'from='+from+'&to='+to;
    } else if(year != -1 && month != -1) {
        var from = year+'-'+month+'-01';
        var newnew_date = new Date(year,month,1);
        var days = new Date(newnew_date.getTime()-1000*60*60*24).getDate();
        var to = year+'-'+month+'-'+days;
        params = 'from='+from+'&to='+to;
    }
    $.post('/api/bill', params, function(data){
        drawTable(data, year, month);
        drawChart(data, year, month);
        drawPie(data, year, month);
    });
}

function drawTable(data, year, month){
    $('#bill-tbody').remove();
    $('#bill-tfoot').remove();
    if(year != -1 && month != -1){
        if(data.length > 0){
            $('#bill-table').append('<tbody id="bill-tbody"></tbody>');
            var totalPay = 0;
            var totalIncome = 0;
            for(var i in data){
                var bill = data[i];
                var pay = 0;
                var income = 0;

                var tr = $('<tr id="'+bill['id']+'"></tr>');
                $('#bill-table > tbody').append(tr);
                var table = $('<tr style="display:none"><td colspan="5" style="padding:0;border:none"><table class="table" style="margin:0;background:#FCF8E3"><tbody></tbody></table></td></tr>');
                $('#bill-table > tbody').append(table);
                for(var j in bill['items']){
                    var item = bill['items'][j];
                    var itemTr = $('<tr></tr>');
                    $(itemTr).append('<td width="15%">'+item['item']+'</td>');
                    var money = parseFloat(item['money']);
                    if(item['type']=='PAY'){
                        pay += money;
                        $(itemTr).append('<td width="25%" class="text-right text-danger">'+money.toFixed(2)+'</td>');
                    } else {
                        income += money;
                        $(itemTr).append('<td width="25%" class="text-right text-success">'+money.toFixed(2)+'</td>');
                    }
                    $(itemTr).append('<td width="50%" class="text-center" colspan="2">'+item['remark']+'</td>');
                    $(itemTr).append('<td width="10%" class="text-center text-info"><label id="'+item['id']+'" bid="'+bill['id']+'" class="del-item" style="cursor:pointer">删除</label></td>');
                    $(table).find('tbody').append(itemTr);
                }
                $(tr).append('<td>'+bill['date']+'</td>');
                $(tr).append('<td class="text-right text-danger pay">'+pay.toFixed(2)+'</td>');
                $(tr).append('<td class="text-right text-success income">'+income.toFixed(2)+'</td>');
                if(pay > income){
                    $(tr).append('<td class="text-danger text-right money">'+(pay-income).toFixed(2)+'</td>');
                } else {
                    $(tr).append('<td class="text-right text-success money">'+(income-pay).toFixed(2)+'</td>');
                }
                $(tr).append('<td class="text-center text-info view-item" style="cursor:pointer">查看</td>');

                totalPay += pay;
                totalIncome += income;
            }
            $('#bill-table').append('<tfoot id="bill-tfoot"></tfoot>');
            var trFoot = $('<tr></tr>');
            $(trFoot).append('<th>结算</th>');
            $(trFoot).append('<th id="total-pay" class="text-right text-danger">'+totalPay.toFixed(2)+'</th>');
            $(trFoot).append('<th id="total-income" class="text-right text-success">'+totalIncome.toFixed(2)+'</th>');
            if(totalPay > totalIncome){
                $(trFoot).append('<th id="total-money" class="text-danger text-right">'+(totalPay-totalIncome).toFixed(2)+'</th>');
            } else {
                $(trFoot).append('<th id="total-money" class="text-right text-success">'+(totalIncome-totalPay).toFixed(2)+'</th>');
            }
            $(trFoot).append('<th></th>');
            $('#bill-table > tfoot').append(trFoot);
            $('.view-item').click(toggleBillItem);
            $('.del-item').click(deleteBillItem);
        } else {
            $('table').append('<tbody id="bill-tbody"><tr><td class="text-center" colspan="5">暂无数据</td></tr></tbody>');
        }
    } else {
        $('#bill-table').append('<tbody id="bill-tbody"></tbody>');
        if(data.length > 0){
            var xAxisData = [];
            var incomeData = [];
            var payData = [];
            var maxPay = 0;
            var maxIn = 0;
            var currentTime;
            var idx = -1;
            for(var i in data){
                if(year == -1 && month == -1){
                    currentTime = data[i]['date'].substring(0, 4);
                } else if(year != -1 && month ==-1) {
                    currentTime = data[i]['date'].substring(0, 7);
                } else {
                    currentTime = data[i]['date'];
                }
                var length = xAxisData.length;
                if(length == 0 || xAxisData[length - 1] != currentTime){
                    xAxisData.push(currentTime);
                    incomeData.push(0);
                    payData.push(0);
                    idx++;
                }
                var items = data[i]['items'];
                for(var j in items){
                    var item = items[j];
                    if(item['type']=='PAY'){
                        payData[idx] += item['money'];
                    } else {
                        incomeData[idx] += item['money'];
                    }
                }
                if(incomeData[idx] > maxIn){
                    maxIn = incomeData[idx];
                }
                if(payData[idx] > maxPay){
                    maxPay = payData[idx];
                }
            }
            var totalPay = 0;
            var totalIncome = 0;
            for(var i = 0; i <= idx; i++){
                totalPay += payData[i];
                totalIncome += incomeData[i];
                if(payData[i] > incomeData[i]){
                    $('#bill-table > tbody').append('<tr><td>'+xAxisData[i]+'</td><td class="text-right text-danger pay">'
                    +payData[i].toFixed(2)+'</td><td class="text-right text-success income">'+incomeData[i].toFixed(2)
                    +'</td><td class="text-danger text-right money">'+(payData[i]-incomeData[i]).toFixed(2)+'</td><td></td></tr>');
                } else {
                    $('#bill-table > tbody').append('<tr><td>'+xAxisData[i]+'</td><td class="text-right text-danger pay">'
                    +payData[i].toFixed(2)+'</td><td class="text-right text-success income">'+incomeData[i].toFixed(2)
                    +'</td><td class="text-success text-right money">'+(incomeData[i]-payData[i]).toFixed(2)+'</td><td></td></tr>');
                }
            }
            $('#bill-table').append('<tfoot id="bill-tfoot"></tfoot>');
            var trFoot = $('<tr></tr>');
            $(trFoot).append('<th>结算</th>');
            $(trFoot).append('<th id="total-pay" class="text-right text-danger">'+totalPay.toFixed(2)+'</th>');
            $(trFoot).append('<th id="total-income" class="text-right text-success">'+totalIncome.toFixed(2)+'</th>');
            if(totalPay > totalIncome){
                $(trFoot).append('<th id="total-money" class="text-danger text-right">'+(totalPay-totalIncome).toFixed(2)+'</th>');
            } else {
                $(trFoot).append('<th id="total-money" class="text-right text-success">'+(totalIncome-totalPay).toFixed(2)+'</th>');
            }
            $(trFoot).append('<th></th>');
            $('#bill-table > tfoot').append(trFoot);
        }
    }
}

function drawPie(data, year, month){
    if(incomePieChart){
        incomePieChart.clear();
        incomePieChart.dispose();
    }
    if(payPieChart){
        payPieChart.clear();
        payPieChart.dispose();
    }
    if(data.length > 0){
        var incomeTags={};
        var payTags={}
        var title;
        if(year == -1 && month == -1){
            title = '';
        } else if(year != -1 && month == -1){
            title = year + '年';
        } else if(year != -1 && month != - 1){
            title = year + '年' + month + '月';
        }
        for(var i in data){
            var items = data[i]['items'];
            for(var j in items){
                var item = items[j];
                if(item['type']=='PAY'){
                    if(payTags[item['item']]){
                        payTags[item['item']] += parseFloat(item['money']);
                    } else {
                        payTags[item['item']] = parseFloat(item['money']);
                    }
                } else {
                    if(incomeTags[item['item']]){
                        incomeTags[item['item']] += parseFloat(item['money']);
                    } else {
                        incomeTags[item['item']] = parseFloat(item['money']);
                    }
                }
            }
        }
        incomePieChart=echarts.init(document.getElementById('incomePie'));
        var incomeTagsArr = [];
        var incomeData = [];
        var i = 0;
        for(var tag in incomeTags){
            var obj = {value:incomeTags[tag].toFixed(2),name:tag};
            incomeData[i]=obj;
            i++;
        }
        incomeData.sort(function(a,b){return b.value-a.value});
        var otherIncome = 0;
        for(var count in incomeData){
            if(count > 5){
                otherIncome += parseFloat(incomeData[count].value);
            } else {
                incomeTagsArr[count]=incomeData[count].name;
            }
        }
        if(otherIncome>0){
            incomeTagsArr[6]='其他';
            incomeData[6]={value:otherIncome.toFixed(2),name:'其他'};
        }
        var incomeOption = {
            title : {
                text: title+'收入',
                x:'center'
            },
            legend: {
                orient:'vertical',
                x:'left',
                data:incomeTagsArr
            },
            toolbox: {
                show : true,
                feature : {
                    saveAsImage : {show: true}
                }
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            calculable : true,
            series : [
                {
                    name:'收入',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:incomeData.slice(0,incomeTagsArr.length)
                }
            ]
        };
        incomePieChart.setOption(incomeOption);

        payPieChart=echarts.init(document.getElementById('payPie'));
        var payTagsArr = [];
        var payData = [];
        var i = 0;
        for(var tag in payTags){
            var obj = {value:payTags[tag].toFixed(2),name:tag};
            payData[i]=obj;
            i++;
        }
        payData.sort(function(a,b){return b.value-a.value});
        var otherPay = 0;
        for(var count in payData){
            if(count > 5){
                otherPay += parseFloat(payData[count].value);
            } else {
                payTagsArr[count]=payData[count].name;
            }
        }
        if(otherPay>0){
            payTagsArr[6]='其他';
            payData[6]={value:otherPay.toFixed(2),name:'其他'};
        }
        var payOption = {
            title : {
                text: title+'支出',
                x:'center'
            },
            legend: {
                orient:'vertical',
                x:'left',
                data:payTagsArr
            },
            toolbox: {
                show : true,
                feature : {
                    saveAsImage : {show: true}
                }
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            calculable : true,
            series : [
                {
                    name:'支出',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:payData.slice(0,payTagsArr.length)
                }
            ]
        };
        payPieChart.setOption(payOption);
    } else {
        incomePieChart = echarts.init(document.getElementById('incomePie'));
        incomePieChart.showLoading({
            text : '暂无数据',
            effect : 'dynamicLine',
            textStyle : {
                fontSize : 30
            }
        });
        payPieChart = echarts.init(document.getElementById('payPie'));
        payPieChart.showLoading({
            text : '暂无数据',
            effect : 'dynamicLine',
            textStyle : {
                fontSize : 30
            }
        });
    }


}

function drawChart(result, year, month){
    if(myChart){
        myChart.clear();
        myChart.dispose();
    }
    if(result.length > 0){
        var xAxisData = [];
        var incomeData = [];
        var payData = [];
        var maxPay = 0;
        var maxIn = 0;
        var currentTime;
        var idx = -1;
        for(var i in result){
            if(year == -1 && month == -1){
                currentTime = result[i]['date'].substring(0, 4);
            } else if(year != -1 && month ==-1) {
                currentTime = result[i]['date'].substring(0, 7);
            } else {
                currentTime = result[i]['date'];
            }
            var length = xAxisData.length;
            if(length == 0 || xAxisData[length - 1] != currentTime){
                xAxisData.push(currentTime);
                incomeData.push(0);
                payData.push(0);
                idx++;
            }
            var items = result[i]['items'];
            for(var j in items){
                var item = items[j];
                if(item['type']=='PAY'){
                    payData[idx] += item['money'];
                } else {
                    incomeData[idx] += item['money'];
                }
            }
            if(incomeData[idx] > maxIn){
                maxIn = incomeData[idx];
            }
            if(payData[idx] > maxPay){
                maxPay = payData[idx];
            }
        }
        for(var i = 0; i <= idx; i++){
            incomeData[i]=incomeData[i].toFixed(2);
            payData[i]=(-payData[i]).toFixed(2);
        }
        var x = 45;
        var x2 = 45;
        if(maxIn < 1000){
            x=53;
            x2=45;
        } else if(maxIn < 10000) {
            x= 63;
            x2= 55;
        } else if(maxIn < 1000000){
            x=73;
            x2=65;
        } else if(maxIn < 10000000){
            x = 83;
            x2 = 75;
        } else if(maxIn < 1000000000){
            x = 95;
            x2 = 87;
        }
        myChart = echarts.init(document.getElementById('chart'));
        var option={
                grid:{
                    x:x,
                    y:40,
                    x2:x2,
                    y2:100
                },
                legend:
                {
                    y:'bottom',
                    data:['收入', '支出']
                },
                color:['#3c763d','#a94442'],
                toolbox: {
                    show : true,
                    padding : [0,40,0,0],
                    feature : {
                        magicType : {show: true, type: ['line', 'bar']},
                        saveAsImage : {show: true}
                    }
                },
                tooltip:{
                    trigger:'axis',
                    axisPointer : {
                        type : 'shadow'
                    }
                },
                xAxis:[
                    {
                        type:'category',
                        boundaryGap:false,
                        axisLabel:
                        {
                            formatter:'{value}',
                            textStyle: {
                                fontFamily: '微软雅黑',
                                fontSize: 12,
                                fontStyle: 'normal',
                                fontWeight: 'bold'
                            }
                        },
                        data:xAxisData
                    }
                ],
                yAxis:[
                    {
                        type:'value',
                        axisLabel:
                        {
                            formatter:'￥{value}',
                            textStyle: {
                                fontFamily: '微软雅黑',
                                fontSize: 12,
                                fontStyle: 'normal',
                                fontWeight: 'bold'
                            }
                        }
                    }
                ],
                series:[
                    {
                        name:'收入',
                        type:'line',
                        smooth:true,
                        stack: '总量',
                        data:incomeData
                    },
                    {
                        name:'支出',
                        type:'line',
                        smooth:true,
                        stack: '总量',
                        data:payData
                    }
                ]
            }
            myChart.setOption(option);
    } else {
        myChart = echarts.init(document.getElementById('chart'));
        myChart.showLoading({
            text : '暂无数据',
            effect : 'dynamicLine',
            textStyle : {
                fontSize : 30
            }
        });
    }
}

function decideShowType(){
    var showType = $('input[name="style"]:checked').val();
    if(showType=='table'){
        $('#chart').hide();
        $('#pie').hide();
        $('#table').show();
    } else if(showType=='chart') {
        $('#table').hide();
        $('#pie').hide();
        $('#chart').show();
    } else {
        $('#table').hide();
        $('#chart').hide();
        $('#pie').show();
    }
}

function toggleBillItem(){
    $(this).parent().next().toggle();
}

function deleteBillItem(){
    var $this = this;
    $.post('/bill/delete/'+$(this).attr("id"),function(data){
        $($this).parent().parent().remove();
        var bid=$($this).attr('bid');
        var money=0;
        if($($this).parent().prev().prev().hasClass('text-danger')){  //支出
            money = -parseFloat($($this).parent().prev().prev().text());

            var pay = parseFloat($('#'+bid).find('.pay').text())+money;
            $('#'+bid).find('.pay').text(pay.toFixed(2));
            var payment;
            if($('#'+bid).find('.money').hasClass('text-danger')){
                payment = -parseFloat($('#'+bid).find('.money').text())-money;
            } else {
                payment = parseFloat($('#'+bid).find('.money').text())-money;
            }
            if(payment >= 0){
                $('#'+bid).find('.money').text(payment.toFixed(2));
                $('#'+bid).find('.money').removeClass('text-danger').addClass('text-success');
            } else {
                payment = -payment;
                $('#'+bid).find('.money').text(payment.toFixed(2));
                $('#'+bid).find('.money').removeClass('text-success').addClass('text-danger');
            }

            var totalPay = parseFloat($('#total-pay').text()) + money;
            $('#total-pay').text(totalPay.toFixed(2));
            var totalMoney;
            if($('#total-money').hasClass('text-danger')){
                totalMoney = -parseFloat($('#total-money').text())-money;
            } else {
                totalMoney = parseFloat($('#total-money').text())-money;
            }
            if(totalMoney >= 0){
                $('#total-money').text(totalMoney.toFixed(2));
                $('#total-money').removeClass('text-danger').addClass('text-success');
            } else {
                totalMoney = -totalMoney;
                $('#total-money').text(totalMoney.toFixed(2));
                $('#total-money').removeClass('text-success').addClass('text-danger');
            }
        } else {  //收入
            money = parseFloat($($this).parent().prev().prev().text());

            var pay = parseFloat($('#'+bid).find('.income').text()) - money;
            $('#'+bid).find('.income').text(pay.toFixed(2));
            var payment;
            if($('#'+bid).find('.money').hasClass('text-danger')){
                payment = -parseFloat($('#'+bid).find('.money').text())-money;
            } else {
                payment = parseFloat($('#'+bid).find('.money').text())-money;
            }
            if(payment >= 0){
                $('#'+bid).find('.money').text(payment.toFixed(2));
                $('#'+bid).find('.money').removeClass('text-danger').addClass('text-success');
            } else {
                payment = -payment;
                $('#'+bid).find('.money').text(payment.toFixed(2));
                $('#'+bid).find('.money').removeClass('text-success').addClass('text-danger');
            }

            var totalPay = parseFloat($('#total-income').text()) - money;
            $('#total-income').text(totalPay.toFixed(2));
            var totalMoney;
            if($('#total-money').hasClass('text-danger')){
                totalMoney = -parseFloat($('#total-money').text())-money;
            } else {
                totalMoney = parseFloat($('#total-money').text())-money;
            }
            if(totalMoney >= 0){
                $('#total-money').text(totalMoney.toFixed(2));
                $('#total-money').removeClass('text-danger').addClass('text-success');
            } else {
                totalMoney = -totalMoney;
                $('#total-money').text(totalMoney.toFixed(2));
                $('#total-money').removeClass('text-success').addClass('text-danger');
            }
        }
    });
}

function editProfile(){
    $(this).unbind('click');
    var text = $(this).text();
    if(text=='编辑'){
        $('#basic-form').find('.form-group').css('display','block');
        $('#basic-form').find('.form-group').prev().css('display','none');
        $('#basic-form').find('.form-group>input').each(function(){
            $(this).val($(this).parent().prev().text());
        });
        $(this).text('保存');
        $('#edit').click(editProfile);
    } else {
        if(!isEmail($('input[name="email"]'))){
            $('.virgo-tip > span').text('Email错误');
            $('#edit').click(editProfile);
            $('.virgo-tip').show();
            return;
        } else if(!isPhoneNumber($('input[name="phone"]'))){
            $('.virgo-tip > span').text('手机号错误');
            $('#edit').click(editProfile);
            $('.virgo-tip').show();
            return;
        }
        var param = $('#basic-form').serialize();
        $.post('/u/edit', param, function(data){
            if(data){
                $('#basic-form').find('.form-group').css('display','none');
                $('#basic-form').find('.form-group').prev().css('display','block');
                $('#basic-form').find('.form-group>input').each(function(){
                    $(this).parent().prev().text($.trim($(this).val()));
                });
                $('.virgo-tip > span').text('保存信息成功');
                setTimeout(function(){$('.virgo-tip').hide();}, 2000);
                $('#edit').text('编辑');
            } else {
                $('.virgo-tip > span').text('保存信息失败');
            }
            $('.virgo-tip').show();
            $('#edit').click(editProfile);
        });
    }
}

function isEmail(input){
    var email = $.trim(input.val());
    var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    if(pattern.test(email)){
        return true;
    }
    return false;
}

function isPhoneNumber(input){
    var phone = $.trim(input.val());
    var pattern = /^((\s*)|((\+86)|(86))?((15)|(13)|(18))\d{9})$/;
    if(pattern.test(phone)){
        return true;
    }
    return false;
}

function modifyPassword(){
    $(this).unbind('click');
    var text = $(this).text();
    if(text=='修改'){
        $('#password-form').find('.form-group').css('display','block');
        $('#password-form').find('.form-group').prev().css('display','none');
        $(this).text('保存');
        $('#modify').click(modifyPassword);
    } else {
        var param = $('#password-form').serialize();
        $.post('/u/modify', param, function(data){
            if(data){
                $('#password-form').find('.form-group').css('display','none');
                $('#password-form').find('.form-group').prev().css('display','block');
                $('.virgo-tip > span').text('修改密码成功');
                setTimeout(function(){$('.virgo-tip').hide();}, 2000);
                $('#modify').text('修改');
            } else {
                $('.virgo-tip > span').text('修改密码失败');
            }
            $('.virgo-tip').show();
            $('#modify').click(modifyPassword)
        });
    }
}