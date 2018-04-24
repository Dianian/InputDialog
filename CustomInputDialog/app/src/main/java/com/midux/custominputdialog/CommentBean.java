package com.midux.custominputdialog;

/**
 * Created by yu_midux on 2018/4/20.
 */

public class CommentBean {
    String person;
    String comment;

    public CommentBean(String person, String comment) {
        this.person = person;
        this.comment = comment;
    }

    public String getPerson() {
        return person == null ? "" : person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getComment() {
        return comment == null ? "" : comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
