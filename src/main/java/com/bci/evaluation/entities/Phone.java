package com.bci.evaluation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="phone")
public class Phone {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long phoneId;
    
    @Column(name="number")
    private String number;
    
    @Column(name="city_code")
    private String cityCode;
    
    @Column(name="contry_code")
    private String contryCode;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",referencedColumnName = "user_id", nullable = false)
    private User user;


    @Override
    public String toString() {
        return "Phone [id=" + phoneId + ", number=" + number + ", cityCode=" + cityCode + ", contryCode="
                + contryCode + "]";
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getContryCode() {
        return contryCode;
    }

    public void setContryCode(String contryCode) {
        this.contryCode = contryCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
