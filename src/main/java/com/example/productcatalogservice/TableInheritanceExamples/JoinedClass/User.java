package com.example.productcatalogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Inheritance(strategy= InheritanceType.JOINED)
@Entity(name="jc_user")
public class User {

    @Id
    private Long id;
    private String email;
}
