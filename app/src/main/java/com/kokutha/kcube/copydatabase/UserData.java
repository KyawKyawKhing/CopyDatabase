package com.kokutha.kcube.copydatabase;

class UserData {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String imagedata;

    public UserData(String id, String name, String phone, String address, String imagedata) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.imagedata = imagedata;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImagedata() {
        return imagedata;
    }
}
