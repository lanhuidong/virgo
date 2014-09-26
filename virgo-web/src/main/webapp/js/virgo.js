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
            window.location.href='/'
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
    $.post('/bill', $('#query-form').serialize(), function(data){
        $('table').find('tbody').remove();
        $('table').find('tfoot').remove();
        $('table').append(data);
        $('.view-item').click(toggleBillItem);
        $('.del-item').click(deleteBillItem);
        drawChart();
    });
}

function drawChart(){
    if(myChart){
        myChart.clear();
        myChart.dispose();
    }
    $.ajax({
        url:'/api/bill',
        data:$('#query-form').serialize(),
        type:'post',
        success:function(result){
            if(result.length > 0){
                var xAxisData = [];
                var incomeData = [];
                var payData = [];
                var maxPay = 0;
                var maxIn = 0;
                for(var i in result){
                    xAxisData[i] = result[i]['date'];
                    incomeData[i] = 0;
                    payData[i] = 0;
                    var items = result[i]['items'];
                    for(var j in items){
                        var item = items[j];
                        if(item['type']=='PAY'){
                            payData[i] += parseFloat(item['money']);
                        } else {
                            incomeData[i] += parseFloat(item['money']);
                        }
                    }
                    if(incomeData[i] > maxIn){
                        maxIn = incomeData[i];
                    }
                    if(payData[i] > maxPay){
                        maxPay = payData[i];
                    }

                }
                var x = 45;
                var x2 = 45;
                if(maxIn < 1000){
                    x=45;
                    x2=45;
                } else if(maxIn < 10000) {
                    x= 55;
                    x2= 55;
                } else if(maxIn < 1000000){
                    x=65;
                    x2=65;
                } else if(maxIn < 10000000){
                    x = 75;
                    x2 = 75;
                } else if(maxIn < 1000000000){
                    x = 87;
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
                            data:['支出', '收入']
                        },
                        color:['#a94442','#3c763d'],
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
                            formatter: function(v) {
                                return v[0][1] + '<br/>'
                                       + v[0][0] + ' : ' + v[0][2] + '元<br/>'
                                       + v[1][0] + ' : ' + v[1][2] + '元';
                            }
                        },
                        xAxis:[
                            {
                                type:'category',
                                boundaryGap:false,
                                axisLabel:
                                {
                                    rotate:-60,
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
                                name:'支出',
                                nameTextStyle:{
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    color: '#a94442'
                                },
                                type:'value',
                                boundaryGap:[0, 0],
                                axisLine: {
                                    lineStyle:
                                    {
                                        color: '#a94442'
                                    }
                                },
                                axisLabel:
                                {
                                    formatter:'￥{value}',
                                    textStyle: {
                                        fontFamily: '微软雅黑',
                                        fontSize: 12,
                                        fontStyle: 'normal',
                                        fontWeight: 'bold',
                                        color: '#a94442'
                                    }
                                }
                            },
                            {
                                name:'收入',
                                nameTextStyle:{
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    color: '#3c763d'
                                },
                                position:'right',
                                type:'value',
                                boundaryGap:[0, 0],
                                axisLine: {
                                    lineStyle:
                                    {
                                        color: '#3c763d'
                                    }
                                },
                                axisLabel:
                                {
                                    formatter:'￥{value}',
                                    textStyle: {
                                        fontFamily: '微软雅黑',
                                        fontSize: 12,
                                        fontStyle: 'normal',
                                        fontWeight: 'bold',
                                        color: '#3c763d'
                                    }
                                }
                            }
                        ],
                        series:[
                            {
                                name:'支出',
                                type:'line',
                                data:payData
                            },
                            {
                                name:'收入',
                                yAxisIndex:1,
                                type:'line',
                                data:incomeData
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
    });
}

function decideShowType(){
    var showType = $('input[name="style"]:checked').val();
    if(showType=='table'){
        $('#chart').hide();
        $('#table').show();
    } else {
        $('#table').hide();
        $('#chart').show();
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


//添加任务
function addTodo(){
    $('#addTodo').unbind('click');
    if(stringLength($('#todo'), 1, 20)){
        $.post('add', $('#todo-form').serialize(), function(data){
            if(data){
                $('#todo').val('');
                $('#add-tip').text('保存成功');
            } else {
                $('#add-tip').text('保存失败');
            }
            setTimeout(function(){$('#add-tip').text('');},3000);
            $('#addTodo').click(addTodo);
        });
    } else {
        $('#addTodo').click(addTodo);
    }
}

//查询任务
function queryTodos(){
    $.post('/todo',function(data){
        $('#todos').html(data);
        $('#todos').find('button').click(finishTodo);
    });
}

//结束任务
function finishTodo(){
    var id = $(this).attr('todoId');
    $.post('/todo/finish', {id:id}, function(data){
        if(data){
            window.location.reload();
        }
    });
}