package com.example.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Entity(name="tpc_user")
public class User {

    @Id
    private Long id;
    private String email;
}
