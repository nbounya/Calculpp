package com.nassim.calculpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

import static com.nassim.calculpp.MainActivity.EXTRA_MESSAGE;

public class FavoritesFragment extends Fragment implements View.OnClickListener{
    HashMap<String, String> CalculatorDescription = new HashMap();
    HashMap<String, Integer> CalculatorImage = new HashMap();
    HashMap<String, String> CalculatorCategory = new HashMap();

    public FavoritesFragment() {
        fillHashmaps();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_activity, container, false);
        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);

        Resources res = getResources();
        String[] calculatorName = res.getStringArray(R.array.calculatorName);

        ArrayList<String> namesPresentList = new ArrayList<String>();
        String[] namesPresentUncompressed = new String[calculatorName.length];
        Button clearPreferencesButton = (Button) view.findViewById(R.id.clearPreferences);
        clearPreferencesButton.setOnClickListener(this);

        //checks if any calculators have been favorited
        for (int i = 0; i < calculatorName.length; i++) {
            if (sharedPreferences.contains(calculatorName[i])) {
                namesPresentUncompressed[i] = calculatorName[i];
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
            namesPresentFirstLine[i] = CalculatorDescription.get(namesPresent[i]);
            namesPresentImg[i] = CalculatorImage.get(namesPresent[i]);
            namesPresentId[i] = CalculatorCategory.get(namesPresent[i]) + "," + namesPresent[i];
        }

        //displays favorited calculators
        FavoritesAdapter favAdapter = new
                FavoritesAdapter(getActivity(), namesPresentFirstLine, namesPresentImg, namesPresentId);

        ListView listFav;
        listFav = (ListView) view.findViewById(R.id.listFav);
        listFav.setAdapter(favAdapter);

        listFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                displayCalculator(view);
            }
        });
        return view;
    }

    public void displayCalculator(View view) {
        Intent intent = new Intent(getActivity(), DisplayCalculatorActivity.class);
        TextView favIdText = (TextView) view.findViewById(R.id.favIdText);
        String dataFav = favIdText.getText().toString() + "," + "fav";
        intent.putExtra(EXTRA_MESSAGE, dataFav);
        startActivity(intent);
    }

    //refreshes fragment when it becomes visible
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(FavoritesFragment.this).attach(FavoritesFragment.this).commit();
        }
    }

    //clears favorites
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
        "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(FavoritesFragment.this).attach(FavoritesFragment.this).commit();
    }

    private void fillHashmaps(){
        CalculatorDescription.put("betweenTime", "Calculate days between dates");
        CalculatorDescription.put("addTime", "Add or subtract days from date");
        CalculatorDescription.put("circleArea", "Calculate Circle Area");
        CalculatorDescription.put("ellipseArea", "Calculate Ellipse Area");
        CalculatorDescription.put("parallelogramArea", "Calculate Parallelogram Area");
        CalculatorDescription.put("rectangleArea", "Calculate Rectangle Area");
        CalculatorDescription.put("squareArea", "Calculate Square Area");
        CalculatorDescription.put("trapezoidArea", "Calculate Trapezoid Area");
        CalculatorDescription.put("triangleEdgeArea", "Calculate Triangle Area (with 3 Edges)");
        CalculatorDescription.put("triangleHeightArea", "Calculate Triangle Area (with Base and Height)");
        CalculatorDescription.put("bmiMetric", "Calculate BMI in Metric units");
        CalculatorDescription.put("bmiImperial", "Calculate BMI in Imperial units");
        CalculatorDescription.put("idealWeightMetric", "Calculate Ideal Weight in Metric Units");
        CalculatorDescription.put("idealWeightImperial", "Calculate Ideal Weight in Imperial Units");
        CalculatorDescription.put("currentOhm", "Calculate current");
        CalculatorDescription.put("resistanceOhm", "Calculate resistance");
        CalculatorDescription.put("voltageOhm", "Calculate voltage");
        CalculatorDescription.put("probProbabilities", "Calculate probabalities");
        CalculatorDescription.put("permutationProbabilities", "Calculate permutations and combinations");
        CalculatorDescription.put("statistics", "Calculate statistical variables");
        CalculatorDescription.put("cubeVolume", "Calculate Cube volume");
        CalculatorDescription.put("cuboidVolume", "Calculate Cuboid volume");
        CalculatorDescription.put("sphereVolume", "Calculate Sphere volume");
        CalculatorDescription.put("pyramidVolume", "Calculate Pyramid volume");
        CalculatorDescription.put("cylinderVolume", "Calculate Cylinder volume");
        CalculatorDescription.put("coneVolume", "Calculate Cone volume");

        CalculatorImage.put("betweenTime", R.drawable.time);
        CalculatorImage.put("addTime", R.drawable.time);
        CalculatorImage.put("circleArea", R.drawable.area);
        CalculatorImage.put("ellipseArea", R.drawable.area);
        CalculatorImage.put("parallelogramArea", R.drawable.area);
        CalculatorImage.put("rectangleArea", R.drawable.area);
        CalculatorImage.put("squareArea", R.drawable.area);
        CalculatorImage.put("trapezoidArea", R.drawable.area);
        CalculatorImage.put("triangleEdgeArea", R.drawable.area);
        CalculatorImage.put("triangleHeightArea", R.drawable.area);
        CalculatorImage.put("bmiMetric", R.drawable.bmi);
        CalculatorImage.put("bmiImperial", R.drawable.bmi);
        CalculatorImage.put("idealWeightMetric", R.drawable.bmi);
        CalculatorImage.put("idealWeightImperial", R.drawable.bmi);
        CalculatorImage.put("currentOhm", R.drawable.ohms);
        CalculatorImage.put("resistanceOhm", R.drawable.ohms);
        CalculatorImage.put("voltageOhm", R.drawable.ohms);
        CalculatorImage.put("probProbabilities", R.drawable.probability);
        CalculatorImage.put("permutationProbabilities", R.drawable.probability);
        CalculatorImage.put("statistics", R.drawable.statistics);
        CalculatorImage.put("cubeVolume", R.drawable.volume);
        CalculatorImage.put("cuboidVolume", R.drawable.volume);
        CalculatorImage.put("sphereVolume", R.drawable.volume);
        CalculatorImage.put("pyramidVolume", R.drawable.volume);
        CalculatorImage.put("cylinderVolume", R.drawable.volume);
        CalculatorImage.put("coneVolume", R.drawable.volume);

        CalculatorCategory.put("betweenTime", "Time");
        CalculatorCategory.put("addTime", "Time");
        CalculatorCategory.put("circleArea", "Area");
        CalculatorCategory.put("ellipseArea", "Area");
        CalculatorCategory.put("parallelogramArea", "Area");
        CalculatorCategory.put("rectangleArea", "Area");
        CalculatorCategory.put("squareArea", "Area");
        CalculatorCategory.put("trapezoidArea", "Area");
        CalculatorCategory.put("triangleEdgeArea", "Area");
        CalculatorCategory.put("triangleHeightArea", "Area");
        CalculatorCategory.put("bmiMetric", "BMI");
        CalculatorCategory.put("bmiImperial", "BMI");
        CalculatorCategory.put("idealWeightMetric", "BMI");
        CalculatorCategory.put("idealWeightImperial", "BMI");
        CalculatorCategory.put("currentOhm", "Ohm's Law");
        CalculatorCategory.put("resistanceOhm", "Ohm's Law");
        CalculatorCategory.put("voltageOhm", "Ohm's Law");
        CalculatorCategory.put("probProbabilities", "Probability");
        CalculatorCategory.put("permutationProbabilities", "Probability");
        CalculatorCategory.put("statistics", "Statistics");
        CalculatorCategory.put("cubeVolume", "Volume");
        CalculatorCategory.put("cuboidVolume", "Volume");
        CalculatorCategory.put("sphereVolume", "Volume");
        CalculatorCategory.put("pyramidVolume", "Volume");
        CalculatorCategory.put("cylinderVolume", "Volume");
        CalculatorCategory.put("coneVolume", "Volume");
    }
}