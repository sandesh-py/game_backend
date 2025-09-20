package com.sandesh.game_app_sdp.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recharges")
public class Recharge {
    @Id
    private String id;
    private String memberId;
    private Double amount;
    private String note;

    public Recharge() {}

    public Recharge(String id, String memberId, Double amount, String note) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.note = note;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}


