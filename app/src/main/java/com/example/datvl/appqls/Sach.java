package com.example.datvl.appqls;

public class Sach {
    private int Idsach;
    private byte[] avatar;
    private String Tens, theloai;


    public Sach(int idsach, byte[] avatar, String tens, String theloai) {
        Idsach = idsach;
        this.avatar = avatar;
        Tens = tens;
        this.theloai = theloai;
    }

    public int getIdsach() {
        return Idsach;
    }

    public void setIdsach(int idsach) {
        Idsach = idsach;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getTens() {
        return Tens;
    }

    public void setTens(String tens) {
        Tens = tens;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}