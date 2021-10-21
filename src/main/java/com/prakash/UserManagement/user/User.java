package com.prakash.UserManagement.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true,length = 45)
    private  String email;

    @Column(nullable = false,unique = true,length = 20)
    private String password;

    @Column(nullable = false,length = 20,name="first_name")
    private String firstName;

    @Column(nullable = false,length =20,name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private boolean enabled;
}
