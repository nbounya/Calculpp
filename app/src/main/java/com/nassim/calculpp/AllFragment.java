package com.nassim.calculpp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class AllFragment extends Fragment {
    String[] categoryName;
    String[] categoryDescription;
    int[] categoryImage;

    public AllFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Resources res = getResources();
        categoryName = res.getStringArray(R.array.categoryName);
        categoryDescription = res.getStringArray(R.array.categoryDescription);
        categoryImage = res.getIntArray(R.array.categoryImage);

        View view = inflater.inflate(R.layout.all_fragment, container, false);
        AllAdapter allAdapter = new
                AllAdapter(getActivity(), categoryName, categoryDescription, categoryImage);
        ListView listAll;
        listAll = (ListView) view.findViewById(R.id.listAll);
        listAll.setAdapter(allAdapter);

        listAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                displayCategory(view, position);
            }
        });
        return view;
    }

    public void displayCategory(View view, int position) {
        Intent intent = new Intent(getActivity(), DisplayCalculatorActivity.class);
        String defaultId = null;
        switch(categoryName[+ position]){
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

        String dataAll = categoryName[+ position] + "," + defaultId + "," + "all";
        intent.putExtra("com.example.myapp.START_INPUT", dataAll);
        startActivity(intent);
    }

}
