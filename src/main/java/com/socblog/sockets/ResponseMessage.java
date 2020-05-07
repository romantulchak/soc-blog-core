package com.socblog.sockets;

public class ResponseMessage extends Message {
    private boolean isOk;

    public ResponseMessage(String title, boolean isOk) {
        super(title);
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
