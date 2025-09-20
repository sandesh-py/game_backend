package com.sandesh.game_app_sdp.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {
    @Id
    private String id;
    private String name;
    private Double balance;
    private String phone;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Member(String id, String name, Double balance, String phone) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.phone = phone;
    }

    public Member() {
        this.balance = 0.0; // Default balance
    }
    
    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + ", balance=" + balance + ", phone=" + phone + "]";
    }
}
