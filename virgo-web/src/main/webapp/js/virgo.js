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
    $.post('add', $('#bill-form').serialize(), function(data){
        if(data){
            document.getElementById('bill-form').reset();
            $('#add-tip').text('保存成功');
        } else {
            $('#add-tip').text('保存失败');
        }
        setTimeout(function(){$('#add-tip').text('');},3000);
    });
}

//查询账单
function toggleBillItem(){
    $(this).parent().next().toggle();
}

function deleteBillItem(){
    var $this = this;
    $.post('/bill/delete/'+$(this).attr("id"),function(data){
        $($this).parent().parent().remove();
    })
}