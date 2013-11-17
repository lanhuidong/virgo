<#macro html title>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${title?html}</title>
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/virgo.css" />
        <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
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
                        <li>
                            <a href="/${username}">${username}</a>
                        </li>
                        <li>
                            <a href="/logout">退出</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
        <div class="container" id="content">
            <#nested/>
        </div>
        <div class="container text-center">&copy;2013-${.now?string('yyyy')}&nbsp;&nbsp;Lan.&nbsp;&nbsp;All rights reserved.&nbsp;&nbsp;v1.0.0</div>
    </body>
</html>
</#macro>