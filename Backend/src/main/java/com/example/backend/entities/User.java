package com.example.backend.entities;

import com.example.backend.exceptions.InvalidPhoneNumberException;
import com.example.backend.validators.PhoneNumberValidator;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;


import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Expose
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Expose
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    public User(String phoneNumber, String password, Set<Role> roles) throws InvalidPhoneNumberException {
        setPhoneNumber(phoneNumber);
        setPassword(password);
        this.roles = roles;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        phoneNumber = PhoneNumberValidator.normalize(phoneNumber);
        if (PhoneNumberValidator.isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return;
        }

        throw new InvalidPhoneNumberException("Invalid Phone Number");
    }


    public void setPassword(String password) {
        if (!checkPassword(password)) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }

    public boolean checkPassword(String password) {
        return this.password != null && BCrypt.checkpw(password, this.password);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
