package com.bci.evaluation.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="user_id")
    private UUID userId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    private Date created;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified")
    private Date modified;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Date lastLogin;
    
    @Column(name="is_active")
    private boolean isActive;
    
    @JsonIgnore
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Phone> phones;

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
                + ", created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin + ", isActive="
                + isActive + "]";
    }
    
}
