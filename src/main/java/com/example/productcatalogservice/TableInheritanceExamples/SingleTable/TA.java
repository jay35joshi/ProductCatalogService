package com.example.productcatalogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue("1")
public class TA extends User {
    private int hours;
}
