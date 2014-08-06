<div class="text-info">${.now?string('yyyy-MM-dd')}</div>
<hr/>
<#if todos?? && todos?size gt 0>
    <div>
        <#list todos as todo>
            <li style="margin:5px 0">
                ${todo.content}
                <button type="button" class="btn btn-primary" todoId=${todo.id}>完成</button>
                <#if todo.repeatType != 'NO_REPEAT'>
                <button type="button" class="btn btn-primary" todoId=${todo.id}>结束</button>
                </#if>
            </li>
        </#list>
    </div>
<#else>
    <div>暂无未完成事项</div>
</#if>