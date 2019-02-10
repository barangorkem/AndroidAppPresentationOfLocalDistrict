package com.example.baran.mobileprogrammingproject;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Comments implements Serializable {
    @Exclude private String id;
    private String commentcontent;
    private String userid;
    private String commentdate;
    private String restoranid;
    private String commenttime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getRestoranid() {
        return restoranid;
    }

    public void setRestoranid(String restoranid) {
        this.restoranid = restoranid;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public Comments(String commentcontent, String userid, String commentdate, String restoranid, String commenttime) {
        this.commentcontent = commentcontent;
        this.userid = userid;
        this.commentdate = commentdate;
        this.restoranid = restoranid;
        this.commenttime = commenttime;

    }
}
