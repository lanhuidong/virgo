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
        });
    }
}

function isNumber(input){
    var number = $.trim(input.val());
    var pattern = /^[1-9]\d*(.\d{1,2})?$/;
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
        $('table').remove();
        $('#query-form').after(data);
        $('.view-item').click(toggleBillItem);
        $('.del-item').click(deleteBillItem);
    });
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
        $('#basic-form').find('.form-group').css('display','none');
        $('#basic-form').find('.form-group').prev().css('display','block');
        $('#basic-form').find('.form-group>input').each(function(){
            $(this).parent().prev().text($.trim($(this).val()));
        });
        $(this).text('编辑');
    }
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
        $('#password-form').find('.form-group').css('display','none');
        $('#password-form').find('.form-group').prev().css('display','block');
        $(this).text('修改');
    }
}