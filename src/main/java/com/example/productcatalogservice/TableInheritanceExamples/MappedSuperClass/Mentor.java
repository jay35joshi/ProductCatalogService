package com.example.productcatalogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private double rating;
}
