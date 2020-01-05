package com.example.calculpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.calculpp.MainActivity.EXTRA_MESSAGE;

public class FavoritesActivity extends Fragment implements View.OnClickListener{
    HashMap<String, String> favFirstLine = new HashMap<String, String>();
    HashMap<String, Integer> favImageId = new HashMap<String, Integer>();
    HashMap<String, String> favCategory = new HashMap<String, String>();
    Button clearPreferencesButton;

    public static String[] names = {"betweenTime", "addTime", "circleArea", "ellipseArea", "parallelogramArea", "rectangleArea", "squareArea", "trapezoidArea",
            "triangleEdgeArea", "triangleHeightArea", "bmiMetric", "bmiImperial", "idealWeightMetric", "idealWeightImperial", "currentOhm",
            "resistanceOhm", "voltageOhm", "probProbabilities", "permutationProbabilities", "statistics", "cubeVolume", "sphereVolume",
            "pyramidVolume", "cylinderVolume", "coneVolume"};

    public FavoritesActivity() {
        favFirstLine.put("betweenTime", "Calculate days between dates");
        favFirstLine.put("addTime", "Add or subtract days from date");
        favFirstLine.put("circleArea", "Calculate Circle Area");
        favFirstLine.put("ellipseArea", "Calculate Ellipse Area");
        favFirstLine.put("parallelogramArea", "Calculate Parallelogram Area");
        favFirstLine.put("rectangleArea", "Calculate Rectangle Area");
        favFirstLine.put("squareArea", "Calculate Square Area");
        favFirstLine.put("trapezoidArea", "Calculate Trapezoid Area");
        favFirstLine.put("triangleEdgeArea", "Calculate Triangle Area (with 3 Edges)");
        favFirstLine.put("triangleHeightArea", "Calculate Triangle Area (with Base and Height)");
        favFirstLine.put("bmiMetric", "Calculate BMI in Metric units");
        favFirstLine.put("bmiImperial", "Calculate BMI in Imperial units");
        favFirstLine.put("idealWeightMetric", "Calculate Ideal Weight in Metric Units");
        favFirstLine.put("idealWeightImperial", "Calculate Ideal Weight in Imperial Units");
        favFirstLine.put("currentOhm", "Calculate current");
        favFirstLine.put("resistanceOhm", "Calculate resistance");
        favFirstLine.put("voltageOhm", "Calculate voltage");
        favFirstLine.put("probProbabilities", "Calculate probabalities");
        favFirstLine.put("permutationProbabilities", "Calculate permutations and combinations");
        favFirstLine.put("statistics", "Calculate statistical variables");
        favFirstLine.put("cubeVolume", "Calculate Cube volume");
        favFirstLine.put("cuboidVolume", "Calculate Cuboid volume");
        favFirstLine.put("sphereVolume", "Calculate Sphere volume");
        favFirstLine.put("pyramidVolume", "Calculate Pyramid volume");
        favFirstLine.put("cylinderVolume", "Calculate Cylinder volume");
        favFirstLine.put("coneVolume", "Calculate Cone volume");

        favImageId.put("betweenTime", R.drawable.time);
        favImageId.put("addTime", R.drawable.time);
        favImageId.put("circleArea", R.drawable.area);
        favImageId.put("ellipseArea", R.drawable.area);
        favImageId.put("parallelogramArea", R.drawable.area);
        favImageId.put("rectangleArea", R.drawable.area);
        favImageId.put("squareArea", R.drawable.area);
        favImageId.put("trapezoidArea", R.drawable.area);
        favImageId.put("triangleEdgeArea", R.drawable.area);
        favImageId.put("triangleHeightArea", R.drawable.area);
        favImageId.put("bmiMetric", R.drawable.bmi);
        favImageId.put("bmiImperial", R.drawable.bmi);
        favImageId.put("idealWeightMetric", R.drawable.bmi);
        favImageId.put("idealWeightImperial", R.drawable.bmi);
        favImageId.put("currentOhm", R.drawable.ohms);
        favImageId.put("resistanceOhm", R.drawable.ohms);
        favImageId.put("voltageOhm", R.drawable.ohms);
        favImageId.put("probProbabilities", R.drawable.probability);
        favImageId.put("permutationProbabilities", R.drawable.probability);
        favImageId.put("statistics", R.drawable.statistics);
        favImageId.put("cubeVolume", R.drawable.volume);
        favImageId.put("cuboidVolume", R.drawable.volume);
        favImageId.put("sphereVolume", R.drawable.volume);
        favImageId.put("pyramidVolume", R.drawable.volume);
        favImageId.put("cylinderVolume", R.drawable.volume);
        favImageId.put("coneVolume", R.drawable.volume);

        favCategory.put("betweenTime", "Time");
        favCategory.put("addTime", "Time");
        favCategory.put("circleArea", "Area");
        favCategory.put("ellipseArea", "Area");
        favCategory.put("parallelogramArea", "Area");
        favCategory.put("rectangleArea", "Area");
        favCategory.put("squareArea", "Area");
        favCategory.put("trapezoidArea", "Area");
        favCategory.put("triangleEdgeArea", "Area");
        favCategory.put("triangleHeightArea", "Area");
        favCategory.put("bmiMetric", "BMI");
        favCategory.put("bmiImperial", "BMI");
        favCategory.put("idealWeightMetric", "BMI");
        favCategory.put("idealWeightImperial", "BMI");
        favCategory.put("currentOhm", "Ohm's Law");
        favCategory.put("resistanceOhm", "Ohm's Law");
        favCategory.put("voltageOhm", "Ohm's Law");
        favCategory.put("probProbabilities", "Probability");
        favCategory.put("permutationProbabilities", "Probability");
        favCategory.put("statistics", "Statistics");
        favCategory.put("cubeVolume", "Volume");
        favCategory.put("cuboidVolume", "Volume");
        favCategory.put("sphereVolume", "Volume");
        favCategory.put("pyramidVolume", "Volume");
        favCategory.put("cylinderVolume", "Volume");
        favCategory.put("coneVolume", "Volume");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favorites_activity, container, false);
        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        ArrayList<String> namesPresentList = new ArrayList<String>();
        String[] namesPresentUncompressed = new String[names.length];
        clearPreferencesButton = (Button) view.findViewById(R.id.clearPreferences);
        clearPreferencesButton.setOnClickListener(this);

        for (int i = 0; i < names.length; i++) {
            if (sharedPreferences.contains(names[i])) {
                namesPresentUncompressed[i] = names[i];
            } else {
                namesPresentUncompressed[i] = "";
            }
        }

        for (String s : namesPresentUncompressed)
            if (!s.equals("") && (s != null))
                namesPresentList.add(s);

        String[] namesPresent = new String[namesPresentList.size()];
        namesPresent = namesPresentList.toArray(namesPresent);
        String[] namesPresentFirstLine = new String[namesPresent.length];
        Integer[] namesPresentImg = new Integer[namesPresent.length];
        String[] namesPresentId = new String[namesPresent.length];

        for (int i = 0; i < namesPresent.length; i++) {
            namesPresentFirstLine[i] = favFirstLine.get(namesPresent[i]);
            namesPresentImg[i] = favImageId.get(namesPresent[i]);
            namesPresentId[i] = favCategory.get(namesPresent[i]) + "," + namesPresent[i];
        }

        FavList favAdapter = new
                FavList(getActivity(), namesPresentFirstLine, namesPresentImg, namesPresentId);

        ListView listFav;
        listFav = (ListView) view.findViewById(R.id.listFav);
        listFav.setAdapter(favAdapter);

        listFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                inputsOpen(view);
            }
        });
        return view;
    }

    public void inputsOpen(View view) {
        Intent intent = new Intent(getActivity(), DisplayAllInputsActivity.class);
        TextView favIdText = (TextView) view.findViewById(R.id.favIdText);
        String dataFav = favIdText.getText().toString() + "," + "fav";
        intent.putExtra(EXTRA_MESSAGE, dataFav);
        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(FavoritesActivity.this).attach(FavoritesActivity.this).commit();
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
        "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(FavoritesActivity.this).attach(FavoritesActivity.this).commit();
    }
}