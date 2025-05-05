package com.example.productcatalogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.*;

@MappedSuperclass
public class User {

    @Id
    private Long id;
    private String email;
}
