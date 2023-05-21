package com.nassim.calculpp.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Category.class, Calculator.class, CalculatorCategory.class }, version = 1)
public abstract class CalculatorDB extends RoomDatabase{

    public abstract CalculatorDAO getCalculatorDAO();

    private static CalculatorDB calculatorDB;
}
