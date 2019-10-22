package com.example.program1.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pengguna {

    public static final String Admin = "admin";
    public static final String Konsumen = "konsumen";



    private String id;
    private String nama;
    private String email;
    private String level;
    private String password;

    public Pengguna() {
        // Konstruktor default dibutuhkan untuk dapat dipanggil oleh DataSnapshot.getValue(User.class)
    }

    public Pengguna(String id, String nama, String email, String level, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.level = level;
        this.password = password;

    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
