package com.pharm.online.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor

@Entity
public class OrderMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_order_seq")
    @SequenceGenerator(name = "book_order_seq", sequenceName = "book_order_sequnce", allocationSize = 1)
    private int orderId;
    private String medicineName;
    private String category;
    private String company;
    private String orderedDate;
    private String arivalDate;
    private long quantity;

    public OrderMedicine(int orderId, String medicineName, String category, String company, String orderedDate, String arivalDate, long quantity) {
        this.orderId = orderId;
        this.medicineName = medicineName;
        this.category = category;
        this.company = company;
        this.orderedDate = orderedDate;
        this.arivalDate = arivalDate;
        this.quantity = quantity;
    }
}

