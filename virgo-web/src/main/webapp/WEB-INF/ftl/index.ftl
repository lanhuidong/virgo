<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>记账本-首页</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/virgo.css" />
    <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/virgo.js"></script>
</head>

<body>
    <a href="#content" class="sr-only">Skip to main content</a>
    <header class="navbar navbar-inverse navbar-fixed-top" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">记账本</a>
            </div>
            <nav class="collapse navbar-collapse" role="navigation">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="/">首页</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown">登录</a>
                        <div class="dropdown-menu virgo-dropdown">
                            <form role="form" id="login-form" method="post" action="/login">
                                <div class="form-group">
                                    <label for="username">用户名</label>
                                    <input type="text" class="form-control" name="j_username" id="username" placeholder="请输入用户名" />
                                </div>
                                <div class="form-group">
                                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                                    <input type="password" class="form-control" name="j_password" id="password" placeholder="请输入密码" />
                                </div>
                                <button type="submit" class="btn btn-primary">登录</button>
                            </form>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown">注册</a>
                        <div class="dropdown-menu virgo-dropdown">
                            <form role="form" id="signup-form" method="post" action="/login">
                                <div class="form-group" id="sign-username-fg">
                                    <label for="regusername">用户名</label><label id="sign-username-error" class="text-danger virgo-input-error"></label>
                                    <input type="text" class="form-control" name="j_username" id="regusername" placeholder="4-20个字符" />
                                </div>
                                <div class="form-group" id="sign-password-fg">
                                    <label for="regpassword">密&nbsp;&nbsp;&nbsp;&nbsp;码</label><label id="sign-password-error" class="text-danger virgo-input-error"></label>
                                    <input type="password" class="form-control" name="j_password" id="regpassword" placeholder="6-20个字符" />
                                </div>
                                <button type="submit" id="sign-btn" class="btn btn-primary">注册</button>
                            </form>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    <div class="container" id="content">
        This is main page content.
    </div>
    <div class="container text-center">&copy;2013-${.now?string('yyyy')}&nbsp;&nbsp;Lan.&nbsp;&nbsp;All rights reserved.&nbsp;&nbsp;v1.0.0</div>
</body>
</html>