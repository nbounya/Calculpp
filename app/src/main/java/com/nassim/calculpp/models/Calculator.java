package com.nassim.calculpp.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    public String categoryName;

    public String categoryDescription;
    public int categoryImage;

    @Embedded
    public Calculator defaultCalculator;
}

@Entity
public class Calculator {
    @PrimaryKey
    public String calculatorName;

    public String calculatorDescription;
    public boolean isFavorite;

    @Embedded
    public Category category;
}
