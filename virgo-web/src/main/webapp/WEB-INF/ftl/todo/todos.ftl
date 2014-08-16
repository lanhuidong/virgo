<div class="card col-xs-10 col-sm-6 col-xs-offset-1 col-sm-offset-3">
    <div>${.now?string('yyyy-MM-dd')}</div>
    <div class="line"></div>
    <#if todos?? && todos?size gt 0>
    <#list todos as todo>
    <div class="todo-item">${todo_index+1}.${todo.content}<button todoId="${todo.id}" type="button" class="pull-right btn btn-primary btn-xs">完成</button></div>
    </#list>
    <#else>
    <div>今日无任务</div>
    </#if>
</div>
<div style="width:100%;height:30px" class="col-xs-10 col-sm-6 col-xs-offset-1 col-sm-offset-3"></div>
<#list todoMap?keys as key>
    <div style="width:100%;height:10px" class="col-xs-10 col-sm-6 col-xs-offset-1 col-sm-offset-3"></div>
    <div class="card col-xs-10 col-sm-6 col-xs-offset-1 col-sm-offset-3">
        <div>${key}</div>
        <div class="line"></div>
        <#list todoMap[key] as todo>
        <div class="todo-item">${todo_index+1}.${todo.content}<span style="color:#AAA;font-size:11px">(${todo.finished?string('HH:mm:ss')})</span></div>
        </#list>
    </div>
</#list>