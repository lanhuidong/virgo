<#macro html title>
<html>
    <head>
        <title>${title?html}</title>
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.min.css" />
        <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    </head>

    <body>
        <#nested/>
    </body>
</html>
</#macro>