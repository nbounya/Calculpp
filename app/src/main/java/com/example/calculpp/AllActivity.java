package com.example.calculpp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import static com.example.calculpp.MainActivity.EXTRA_MESSAGE;

public class AllActivity extends Fragment {
    String[] firstLine = {
            "Time",
            "Volume",
            "Area",
            "BMI",
            "Statistics",
            "Probability",
            "Ohm's Law"
    } ;
    String[] secondLine = {
            "Calculations on time.",
            "Calculate volume of shapes.",
            "Calculate area of shapes.",
            "Calculate body BMI.",
            "Calculate statistical values.",
            "Calculate probabilities, permutations and combinations.",
            "Calculate current, voltage and resistance."
    } ;
    Integer[] imageId = {
            R.drawable.time,
            R.drawable.volume,
            R.drawable.area,
            R.drawable.bmi,
            R.drawable.statistics,
            R.drawable.probability,
            R.drawable.ohms
    };

    public AllActivity(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_activity, container, false);
        AllList allAdapter = new
                AllList(getActivity(), firstLine, secondLine, imageId);
        ListView listAll;
        listAll = (ListView) view.findViewById(R.id.listAll);
        listAll.setAdapter(allAdapter);

        listAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                inputsOpen(view, position);
            }
        });
        return view;
    }

    public void inputsOpen(View view, int position) {
        Intent intent = new Intent(getActivity(), DisplayAllInputsActivity.class);
        String defaultId = null;
        switch(firstLine[+ position]){
            case "Volume":
                defaultId = "cubeVolume";
                break;
            case "Time":
                defaultId = "betweenTime";
                break;
            case "BMI":
                defaultId = "bmiMetric";
                break;
            case "Statistics":
                defaultId = "statistics";
                break;
            case "Probability":
                defaultId = "probProbabilities";
                break;
            case "Ohm's Law":
                defaultId = "currentOhm";
                break;
            case "Area":
                defaultId = "circleArea";
                break;
        }

        String dataAll = firstLine[+ position] + "," + defaultId + "," + "all";
        intent.putExtra(EXTRA_MESSAGE, dataAll);
        startActivity(intent);
    }

}
