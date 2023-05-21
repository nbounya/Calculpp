package com.nassim.calculpp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.nassim.calculpp.models.Calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorRepository {

    private static CalculatorRepository instance;
    private ArrayList<Calculator> calculatorList = new ArrayList<>();

    private CalculatorRepository getInstance(){
        if(instance == null){
            instance = new CalculatorRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Calculator>> getCalculators(){

    }

    public void setCalculators(){
        
    }
}
