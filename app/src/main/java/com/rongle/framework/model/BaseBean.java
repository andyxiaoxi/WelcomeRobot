package com.rongle.framework.model;

import java.io.Serializable;

public class BaseBean implements Serializable {

    protected long id = -1;
    protected String serverId;
    protected long modifyTime;
    protected int flags;
    protected int state;

    public static class State {
        public static final int SYNCED = 0;
        public static final int ADD = 1;
        public static final int MODIFY = 2;
        public static final int DELETE = 3;
        public static final int FAILED = 4;
    }

    public BaseBean() {
    }

    public BaseBean(long id, int flags) {
        this.id = id;
        this.flags = flags;
    }

    public BaseBean(String serverId, int flags) {
        this.serverId = serverId;
        this.flags = flags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CodeBean [id=" + id + ", serverId="
                + serverId + ", modifyTime=" + modifyTime + "]";
    }
}
