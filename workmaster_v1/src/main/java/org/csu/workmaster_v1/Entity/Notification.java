package org.csu.workmaster_v1.Entity;

import java.util.Random;

public class Notification {
    public long noficationid;
    public long senderId;
    public long receiver;
    public String notifictioncontent;
    public long time;
    public long groupid;
    public int  notificationstatus;//状态 0  1  2

    public Notification(long senderId, long receiver, String notifictioncontent, long groupid) {
        this.noficationid = new Random().nextLong();
        this.senderId = senderId;
        this.receiver = receiver;
        this.notifictioncontent = notifictioncontent;
        this.time = System.currentTimeMillis();
        this.groupid = groupid;
        this.notificationstatus = 0;
    }

    public long getNoficationid() {
        return noficationid;
    }

    public void setNoficationid(long noficationid) {
        this.noficationid = noficationid;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public String getNotifictioncontent() {
        return notifictioncontent;
    }

    public void setNotifictioncontent(String notifictioncontent) {
        this.notifictioncontent = notifictioncontent;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public int getNotificationstatus() {
        return notificationstatus;
    }

    public void setNotificationstatus(int notificationstatus) {
        this.notificationstatus = notificationstatus;
    }

}
