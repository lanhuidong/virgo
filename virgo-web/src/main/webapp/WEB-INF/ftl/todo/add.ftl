<@c.html title="添加任务-记事本" init="initTodoAdd">
    <div class="row">
        <form id="todo-form" class="form-horizontal" role="form" method="post" action="/todo/add">
            <div class="form-group">
                <label for="date" class="col-xs-3 col-sm-2 control-label text-right">日期</label>
                <div class="col-xs-6 col-sm-2">
                    <input type="text" class="form-control datepicker" style="cursor:pointer" name="remindAt" readonly="readonly" id="date" value="${.now?string('yyyy-MM-dd')}" />
                </div>
            </div>
            <div class="form-group">
                <label for="todo" class="col-xs-3 col-sm-2 control-label text-right">任务</label>
                <div class="col-xs-8 col-sm-4">
                    <input type="text" class="form-control" name="content" id="todo"  placeholder="必填，不能超过20个字符" />
                </div>
            </div>
            <div class="form-group">
                <label for="repeat" class="col-xs-3 col-sm-2 control-label text-right">重复</label>
                <div class="col-xs-4 col-sm-2">
                    <select id="repeat" name="repeatType" class="form-control">
                        <option value="NO_REPEAT">不重复</option>
                        <option value="DAY">每天</option>
                        <option value="WEEK">每周</option>
                        <option value="MONTH">每月</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-sm-2 control-label"></label>
                <div class="col-xs-9 col-sm-10">
                    <button type="button" id="addTodo" class="btn btn-primary">提交</button>
                    <label id="add-tip" class="text-danger virgo-input-error"></label>
                </div>
            </div>
        </form>
    </div>
</@c.html>