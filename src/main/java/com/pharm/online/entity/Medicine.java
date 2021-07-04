package com.pharm.online.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Medicine {

    @Id
    @SequenceGenerator(name = "medicine_sequence", sequenceName = "medicine_sequence", allocationSize = 1, initialValue = 101)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "")
    private int medicineId;
    private String medicineName;
    private String category;
    private String company;
    private String price;
    private long quantity;




    public Medicine(String medicineName, String category, String company) {
        this.medicineName = medicineName;
        this.category = category;
        this.company = company;
    }

    public Medicine(String category, long quantity) {
        this.category = category;
        this.quantity = quantity;
    }
}
