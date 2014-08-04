package com.nexusy.virgo.data.model;

import java.util.Date;

/**
 * @author lan
 * @since 2014-07-31
 */
public class TodoList {

    private Long id;
    private Date created;  //任务创建时间
    private String content;  //任务内容
    private Date remindAt;  //任务提醒时间
    private Date finished;  //任务完成时间
    private LoopType loopType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LoopType getLoopType() {
        return loopType;
    }

    public void setLoopType(LoopType loopType) {
        this.loopType = loopType;
    }

    public Date getRemindAt() {
        return remindAt;
    }

    public void setRemindAt(Date remindAt) {
        this.remindAt = remindAt;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
