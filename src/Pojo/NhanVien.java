/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojo;

import java.util.Date;

/**
 *
 * @author HUU KHANH
 */
public class NhanVien {

    private String id;
    private String nameEmployee;
    private String position;
    private String phone;
    private String gender;
    private Date bod;
    
    public NhanVien(String id, String nameEmployee, String position, String phone, String gender, Date bod) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.position = position;
        this.phone = phone;
        this.gender = gender;
        this.bod = bod;
    }

    public NhanVien() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBod() {
        return bod;
    }

    public void setBod(Date bod) {
        this.bod = bod;
    }
    
}
