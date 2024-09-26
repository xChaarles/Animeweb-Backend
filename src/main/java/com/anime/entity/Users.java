package com.anime.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.uemail=:uemail")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "unombre")               private String unombre;
    @Column(name = "uapellido")             private String uapellido;
    @Column(name = "uemail", unique = true) private String uemail;
    @Column(name = "upassword")             private String upassword;
    @Column(name = "urole")                 private String urole;
    @Column(name = "status")                private String status;

}
