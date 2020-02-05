package com.nassim.calculpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.nassim.calculpp.Methods.addSubtractDate;
import static com.nassim.calculpp.Methods.calculateAverage;
import static com.nassim.calculpp.Methods.calculateBMI;
import static com.nassim.calculpp.Methods.calculateCombination;
import static com.nassim.calculpp.Methods.calculateConeVolume;
import static com.nassim.calculpp.Methods.calculateCount;
import static com.nassim.calculpp.Methods.calculateCubeVolume;
import static com.nassim.calculpp.Methods.calculateCuboidVolume;
import static com.nassim.calculpp.Methods.calculateCylinderVolume;
import static com.nassim.calculpp.Methods.calculateDaysBetweenDates;
import static com.nassim.calculpp.Methods.calculateGeometricMean;
import static com.nassim.calculpp.Methods.calculateIdealWeight;
import static com.nassim.calculpp.Methods.calculateLargest;
import static com.nassim.calculpp.Methods.calculateMedian;
import static com.nassim.calculpp.Methods.calculatePermutation;
import static com.nassim.calculpp.Methods.calculatePopulationStandardDeviation;
import static com.nassim.calculpp.Methods.calculatePyramidVolume;
import static com.nassim.calculpp.Methods.calculateRange;
import static com.nassim.calculpp.Methods.calculateSampleStandardDeviation;
import static com.nassim.calculpp.Methods.calculateSampleVariance;
import static com.nassim.calculpp.Methods.calculateSmallest;
import static com.nassim.calculpp.Methods.calculateSphereVolume;
import static com.nassim.calculpp.Methods.calculateSum;
import static com.nassim.calculpp.Methods.calculateVariance;
import static java.lang.Math.sqrt;

public class DisplayAllInputsActivity extends AppCompatActivity {
    public static final String BACK_REFRESH = "com.example.myfirstapp.BACK_REFRESH";
    public String dataPublic;
    public String dataTab;
    static DecimalFormat form = new DecimalFormat("0.00");
    static DecimalFormat form1 = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String dataAll = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String data[] = dataAll.split(",");
        dataPublic = data[1];
        dataTab = data[2];

        switch (data[0]) {
                case "Volume":
                    setContentView(R.layout.volume_activity);
                    Spinner shapesList = (Spinner) findViewById(R.id.shape);
                    ArrayAdapter<CharSequence> shapesAdapter = ArrayAdapter.createFromResource(this, R.array.shapes_list,
                            android.R.layout.simple_spinner_item);
                    shapesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    shapesList.setAdapter(shapesAdapter);
                    final ImageView shapeIcon = (ImageView) findViewById(R.id.shapeIcon);
                    final ScrollView shapeInputs = findViewById(R.id.shapeInputs);

                    switch(data[1]){
                        case "default":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(0);
                            shapeInputs.addView(View.inflate(this, R.layout.cube_inputs, null));
                            shapeIcon.setImageResource(R.drawable.cube);
                            break;
                        case "cubeVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(0);
                            shapeInputs.addView(View.inflate(this, R.layout.cube_inputs, null));
                            shapeIcon.setImageResource(R.drawable.cube);
                            break;
                        case "cuboidVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(1);
                            shapeInputs.addView(View.inflate(this, R.layout.cuboid_inputs, null));
                            shapeIcon.setImageResource(R.drawable.cuboid);
                            break;
                        case "sphereVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(2);
                            shapeInputs.addView(View.inflate(this, R.layout.sphere_inputs, null));
                            shapeIcon.setImageResource(R.drawable.sphere);
                            break;
                        case "pyramidVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(3);
                            shapeInputs.addView(View.inflate(this, R.layout.pyramid_inputs, null));
                            shapeIcon.setImageResource(R.drawable.pyramid);
                            break;
                        case "cylinderVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(4);
                            shapeInputs.addView(View.inflate(this, R.layout.cylinder_inputs, null));
                            shapeIcon.setImageResource(R.drawable.cylinder);
                            break;
                        case "coneVolume":
                            shapeInputs.removeAllViews();
                            shapesList.setSelection(5);
                            shapeInputs.addView(View.inflate(this, R.layout.cone_inputs, null));
                            shapeIcon.setImageResource(R.drawable.cone);
                            break;
                    }

                    shapesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                shapeInputs.removeAllViews();
                                switch (position) {
                                    case 0:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.cube_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.cube);
                                        break;
                                    case 1:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.cuboid_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.cuboid);
                                        break;
                                    case 2:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.sphere_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.sphere);
                                        break;
                                    case 3:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.pyramid_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.pyramid);
                                        break;
                                    case 4:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.cylinder_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.cylinder);
                                        break;
                                    case 5:
                                        shapeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.cone_inputs, null));
                                        shapeIcon.setImageResource(R.drawable.cone);
                                        break;
                                }
                            }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
                case "Time":
                    setContentView(R.layout.time_activity);
                    Spinner timeList = (Spinner) findViewById(R.id.timeList);
                    ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_list,
                            android.R.layout.simple_spinner_item);
                    timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    timeList.setAdapter(timeAdapter);
                    final ImageView timeIcon = (ImageView) findViewById(R.id.timeIcon);
                    final ScrollView timeInputs = findViewById(R.id.timeInputs);

                    switch(data[1]){
                        case "default":
                            timeInputs.removeAllViews();
                            timeList.setSelection(0);
                            timeInputs.addView(View.inflate(this, R.layout.between_days_inputs, null));
                            timeIcon.setImageResource(R.drawable.betweentime);
                            break;
                        case "betweenTime":
                            timeInputs.removeAllViews();
                            timeList.setSelection(0);
                            timeInputs.addView(View.inflate(this, R.layout.between_days_inputs, null));
                            timeIcon.setImageResource(R.drawable.betweentime);
                            break;
                        case "addTime":
                            timeInputs.removeAllViews();
                            timeList.setSelection(1);
                            timeInputs.addView(View.inflate(this, R.layout.add_subtract_days_inputs, null));
                            timeIcon.setImageResource(R.drawable.addtime);
                            break;
                    }

                    timeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            timeInputs.removeAllViews();
                            switch (position) {
                                case 0:
                                    timeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.between_days_inputs, null));
                                    timeIcon.setImageResource(R.drawable.betweentime);
                                    break;
                                case 1:
                                    timeInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.add_subtract_days_inputs, null));
                                    timeIcon.setImageResource(R.drawable.addtime);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
                case "BMI":
                    setContentView(R.layout.bmi_activity);
                    Spinner bmiList = (Spinner) findViewById(R.id.bmiList);
                    ArrayAdapter<CharSequence> bmiAdapter = ArrayAdapter.createFromResource(this, R.array.bmi_list,
                            android.R.layout.simple_spinner_item);
                    bmiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    bmiList.setAdapter(bmiAdapter);
                    final ImageView bmiIcon = (ImageView) findViewById(R.id.bmiIcon);
                    final ScrollView bmiInputs = findViewById(R.id.bmiInputs);

                    switch(data[1]){
                        case "default":
                            bmiInputs.removeAllViews();
                            bmiList.setSelection(0);
                            bmiInputs.addView(View.inflate(this, R.layout.bmi_metric_inputs, null));
                            bmiIcon.setImageResource(R.drawable.bmi);
                            break;
                        case "bmiMetric":
                            bmiInputs.removeAllViews();
                            bmiList.setSelection(0);
                            bmiInputs.addView(View.inflate(this, R.layout.bmi_metric_inputs, null));
                            bmiIcon.setImageResource(R.drawable.bmi);
                            break;
                        case "bmiImperial":
                            bmiInputs.removeAllViews();
                            bmiList.setSelection(1);
                            bmiInputs.addView(View.inflate(this, R.layout.bmi_imperial_inputs, null));
                            bmiIcon.setImageResource(R.drawable.bmi);
                            break;
                        case "idealWeightMetric":
                            bmiInputs.removeAllViews();
                            bmiList.setSelection(2);
                            bmiInputs.addView(View.inflate(this, R.layout.ideal_metric_inputs, null));
                            bmiIcon.setImageResource(R.drawable.weight);
                            break;
                        case "idealWeightImperial":
                            bmiInputs.removeAllViews();
                            bmiList.setSelection(3);
                            bmiInputs.addView(View.inflate(this, R.layout.ideal_imperial_inputs, null));
                            bmiIcon.setImageResource(R.drawable.weight);
                            break;
                    }

                    bmiList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            bmiInputs.removeAllViews();
                            switch (position) {
                                case 0:
                                    bmiInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.bmi_metric_inputs, null));
                                    bmiIcon.setImageResource(R.drawable.bmi);
                                    break;
                                case 1:
                                    bmiInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.bmi_imperial_inputs, null));
                                    bmiIcon.setImageResource(R.drawable.bmi);
                                    break;
                                case 2:
                                    bmiInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.ideal_metric_inputs, null));
                                    bmiIcon.setImageResource(R.drawable.weight);
                                    break;
                                case 3:
                                    bmiInputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.ideal_imperial_inputs, null));
                                    bmiIcon.setImageResource(R.drawable.weight);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
                case "Statistics":
                    setContentView(R.layout.statistics_activity);
                    ArrayAdapter<CharSequence> statisticsAdapter = ArrayAdapter.createFromResource(this, R.array.bmi_list,
                            android.R.layout.simple_spinner_item);
                    statisticsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    final ScrollView statisticsinputs = findViewById(R.id.statisticsinputs);

                    statisticsinputs.addView(View.inflate(this, R.layout.statistics_inputs, null));
                    break;
                case "Probability":
                    setContentView(R.layout.probability_activity);
                    Spinner probabilityList = (Spinner) findViewById(R.id.probabilityList);
                    ArrayAdapter<CharSequence> probabilityAdapter = ArrayAdapter.createFromResource(this, R.array.probability_list,
                            android.R.layout.simple_spinner_item);
                    probabilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    probabilityList.setAdapter(probabilityAdapter);
                    final ImageView probabilityIcon = (ImageView) findViewById(R.id.probabilityIcon);
                    final ScrollView probabilityinputs = findViewById(R.id.probabilityinputs);

                    switch(data[1]){
                        case "default":
                            probabilityinputs.removeAllViews();
                            probabilityList.setSelection(0);
                            probabilityinputs.addView(View.inflate(this, R.layout.probability_inputs, null));
                            probabilityIcon.setImageResource(R.drawable.probability);
                            break;
                        case "probProbabilities":
                            probabilityinputs.removeAllViews();
                            probabilityList.setSelection(0);
                            probabilityinputs.addView(View.inflate(this, R.layout.probability_inputs, null));
                            probabilityIcon.setImageResource(R.drawable.probability);
                            break;
                        case "permutationProbabilities":
                            probabilityinputs.removeAllViews();
                            probabilityList.setSelection(1);
                            probabilityinputs.addView(View.inflate(this, R.layout.permutation_inputs, null));
                            probabilityIcon.setImageResource(R.drawable.permutation);
                            break;
                    }

                    probabilityList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            probabilityinputs.removeAllViews();
                            switch (position) {
                                case 0:
                                    probabilityinputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.probability_inputs, null));
                                    probabilityIcon.setImageResource(R.drawable.probability);
                                    break;
                                case 1:
                                    probabilityinputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.permutation_inputs, null));
                                    probabilityIcon.setImageResource(R.drawable.permutation);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
                case "Ohm's Law":
                    setContentView(R.layout.ohm_activity);
                    Spinner ohmList = (Spinner) findViewById(R.id.ohmList);
                    ArrayAdapter<CharSequence> ohmAdapter = ArrayAdapter.createFromResource(this, R.array.ohm_list,
                            android.R.layout.simple_spinner_item);
                    ohmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ohmList.setAdapter(ohmAdapter);
                    final ImageView ohmIcon = (ImageView) findViewById(R.id.ohmIcon);
                    final ScrollView ohminputs = findViewById(R.id.ohminputs);
                    ohminputs.addView(View.inflate(this, R.layout.current_inputs, null));

                    switch(data[1]){
                        case "default":
                            ohminputs.removeAllViews();
                            ohmList.setSelection(0);
                            ohminputs.addView(View.inflate(this, R.layout.current_inputs, null));
                            ohmIcon.setImageResource(R.drawable.current);
                            break;
                        case "currentOhm":
                            ohminputs.removeAllViews();
                            ohmList.setSelection(0);
                            ohminputs.addView(View.inflate(this, R.layout.current_inputs, null));
                            ohmIcon.setImageResource(R.drawable.current);
                            break;
                        case "resistanceOhm":
                            ohminputs.removeAllViews();
                            ohmList.setSelection(1);
                            ohminputs.addView(View.inflate(this, R.layout.resistance_inputs, null));
                            ohmIcon.setImageResource(R.drawable.resistance);
                            break;
                        case "voltageOhm":
                            ohminputs.removeAllViews();
                            ohmList.setSelection(2);
                            ohminputs.addView(View.inflate(this, R.layout.voltage_inputs, null));
                            ohmIcon.setImageResource(R.drawable.voltage);
                            break;
                    }

                    ohmList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            ohminputs.removeAllViews();
                            switch (position) {
                                case 0:
                                    ohminputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.current_inputs, null));
                                    ohmIcon.setImageResource(R.drawable.current);
                                    break;
                                case 1:
                                    ohminputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.resistance_inputs, null));
                                    ohmIcon.setImageResource(R.drawable.resistance);
                                    break;
                                case 2:
                                    ohminputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.voltage_inputs, null));
                                    ohmIcon.setImageResource(R.drawable.voltage);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
                case "Area":
                    setContentView(R.layout.area_activity);
                    Spinner areaList = (Spinner) findViewById(R.id.areaList);
                    ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(this, R.array.area_list,
                            android.R.layout.simple_spinner_item);
                    areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    areaList.setAdapter(areaAdapter);
                    final ImageView areaIcon = (ImageView) findViewById(R.id.areaIcon);
                    final ScrollView areainputs = findViewById(R.id.areaInputs);

                    switch(data[1]){
                        case "default":
                            areainputs.removeAllViews();
                            areaList.setSelection(0);
                            areainputs.addView(View.inflate(this, R.layout.circle_inputs, null));
                            areaIcon.setImageResource(R.drawable.circle);
                            break;
                        case "circleArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(0);
                            areainputs.addView(View.inflate(this, R.layout.circle_inputs, null));
                            areaIcon.setImageResource(R.drawable.circle);
                            break;
                        case "ellipseArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(1);
                            areainputs.addView(View.inflate(this, R.layout.ellipse_inputs, null));
                            areaIcon.setImageResource(R.drawable.ellipse);
                            break;
                        case "parallelogramArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(2);
                            areainputs.addView(View.inflate(this, R.layout.parallelogram_inputs, null));
                            areaIcon.setImageResource(R.drawable.parallelogram);
                            break;
                        case "rectangleArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(3);
                            areainputs.addView(View.inflate(this, R.layout.rectangle_inputs, null));
                            areaIcon.setImageResource(R.drawable.rectangle);
                            break;
                        case "squareArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(4);
                            areainputs.addView(View.inflate(this, R.layout.square_inputs, null));
                            areaIcon.setImageResource(R.drawable.square);
                            break;
                        case "trapezoidArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(5);
                            areainputs.addView(View.inflate(this, R.layout.trapezoid_inputs, null));
                            areaIcon.setImageResource(R.drawable.trapezoid);
                            break;
                        case "triangleEdgeArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(6);
                            areainputs.addView(View.inflate(this, R.layout.triangle_edge_inputs, null));
                            areaIcon.setImageResource(R.drawable.triangleedge);
                            break;
                        case "triangleHeightArea":
                            areainputs.removeAllViews();
                            areaList.setSelection(7);
                            areainputs.addView(View.inflate(this, R.layout.triangle_height_inputs, null));
                            areaIcon.setImageResource(R.drawable.triangleheight);
                            break;
                    }

                    areaList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            areainputs.removeAllViews();
                            switch (position) {
                                case 0:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.circle_inputs, null));
                                    areaIcon.setImageResource(R.drawable.circle);
                                    break;
                                case 1:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.ellipse_inputs, null));
                                    areaIcon.setImageResource(R.drawable.ellipse);
                                    break;
                                case 2:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.parallelogram_inputs, null));
                                    areaIcon.setImageResource(R.drawable.parallelogram);
                                    break;
                                case 3:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.rectangle_inputs, null));
                                    areaIcon.setImageResource(R.drawable.rectangle);
                                    break;
                                case 4:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.square_inputs, null));
                                    areaIcon.setImageResource(R.drawable.square);
                                    break;
                                case 5:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.trapezoid_inputs, null));
                                    areaIcon.setImageResource(R.drawable.trapezoid);
                                    break;
                                case 6:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.triangle_edge_inputs, null));
                                    areaIcon.setImageResource(R.drawable.triangleedge);
                                    break;
                                case 7:
                                    areainputs.addView(View.inflate(DisplayAllInputsActivity.this, R.layout.triangle_height_inputs, null));
                                    areaIcon.setImageResource(R.drawable.triangleheight);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // your code here
                        }

                    });
                    break;
            }

        Handler handler = new Handler();
        handler.postDelayed(updateFavButton, 110);
    }

    private Runnable updateFavButton = new Runnable() {
        public void run() {
            SharedPreferences sharedPreferences = getSharedPreferences(
                    "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
            if(sharedPreferences.contains(dataPublic)){
                int FavButtonId = getResources().getIdentifier(dataPublic + "ToggleButton", "id", getPackageName());
                ToggleButton FavoriteButton = (ToggleButton) findViewById(FavButtonId);
                FavoriteButton.setChecked(true);
                FavoriteButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.img_star_selected));
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this, MainActivity.class);
            a.putExtra(BACK_REFRESH, dataTab);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void displayCubeVolumeResult(View view){
        EditText cubeSideLength = (EditText) findViewById(R.id.cubeSideLength);
        TextView cubeVolumeResult = (TextView) findViewById(R.id.cubeResultNumber);
        String cubeSideLengthText = cubeSideLength.getText().toString();

        if(cubeSideLengthText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double length = Double.parseDouble(cubeSideLengthText);
        double cubeVolume = calculateCubeVolume(length);
        String cubeVolumeString = form.format(cubeVolume);
        cubeVolumeResult.setText(cubeVolumeString);
    }

    public void displayCuboidVolumeResult(View view){
        EditText cubeSideLength1 = (EditText) findViewById(R.id.cuboidSideLength1);
        EditText cubeSideLength2 = (EditText) findViewById(R.id.cuboidSideLength2);
        EditText cubeSideLength3 = (EditText) findViewById(R.id.cuboidSideLength3);
        TextView cubeVolumeResult = (TextView) findViewById(R.id.cuboidResultNumber);
        String cubeSideLength1Text = cubeSideLength1.getText().toString();
        String cubeSideLength2Text = cubeSideLength2.getText().toString();
        String cubeSideLength3Text = cubeSideLength3.getText().toString();

        if(cubeSideLength1Text.isEmpty()||cubeSideLength2Text.isEmpty()||cubeSideLength3Text.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double length1 = Double.parseDouble(cubeSideLength1Text);
        double length2 = Double.parseDouble(cubeSideLength2Text);
        double length3 = Double.parseDouble(cubeSideLength3Text);
        double cuboidVolume = calculateCuboidVolume(length1, length2, length3);
        String cuboidVolumeString = form.format(cuboidVolume);
        cubeVolumeResult.setText(cuboidVolumeString);
    }

    public void displaySphereVolumeResult(View view){
        EditText sphereRadiusNumber = (EditText) findViewById(R.id.sphereRadiusNumber);
        TextView sphereResultNumber = (TextView) findViewById(R.id.sphereResultNumber);
        String sphereRadiusNumberText = sphereRadiusNumber.getText().toString();

        if(sphereRadiusNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double radius = Double.parseDouble(sphereRadiusNumberText);
        double sphereVolume = calculateSphereVolume(radius);
        String sphereVolumeString = form.format(sphereVolume);
        sphereResultNumber.setText(sphereVolumeString);
    }

    public void displayPyramidVolumeResult(View view){
        EditText pyramidLengthNumber = (EditText) findViewById(R.id.pyramidLengthNumber);
        EditText pyramidWidthNumber = (EditText) findViewById(R.id.pyramidWidthNumber);
        EditText pyramidHeightNumber = (EditText) findViewById(R.id.pyramidHeightNumber);
        TextView pyramidResultNumber = (TextView) findViewById(R.id.pyramidResultNumber);
        String pyramidLengthNumberText = pyramidLengthNumber.getText().toString();
        String pyramidWidthNumberText = pyramidWidthNumber.getText().toString();
        String pyramidHeightNumberText = pyramidHeightNumber.getText().toString();

        if(pyramidLengthNumberText.isEmpty()||pyramidWidthNumberText.isEmpty()||pyramidHeightNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double length = Double.parseDouble(pyramidLengthNumberText);
        double width = Double.parseDouble(pyramidWidthNumberText);
        double heigth = Double.parseDouble(pyramidHeightNumberText);
        double pyramidVolume = calculatePyramidVolume(length, width, heigth);
        String pyramidVolumeString = form.format(pyramidVolume);
        pyramidResultNumber.setText(pyramidVolumeString);
    }

    public void displayCylinderVolumeResult(View view){
        EditText cylinderRadiusNumber = (EditText) findViewById(R.id.cylinderRadiusNumber);
        EditText cylinderHeightNumber = (EditText) findViewById(R.id.cylinderHeightNumber);
        TextView cylinderResultNumber = (TextView) findViewById(R.id.cylinderResultNumber);
        String cylinderRadiusNumberText = cylinderRadiusNumber.getText().toString();
        String cylinderHeightNumberText = cylinderHeightNumber.getText().toString();

        if(cylinderRadiusNumberText.isEmpty()||cylinderHeightNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double radius = Double.parseDouble(cylinderRadiusNumberText);
        double height = Double.parseDouble(cylinderHeightNumberText);
        double cylinderVolume = calculateCylinderVolume(radius, height);
        String cylinderVolumeString = form.format(cylinderVolume);
        cylinderResultNumber.setText(cylinderVolumeString);
    }

    public void displayConeVolumeResult(View view){
        EditText coneRadiusNumber = (EditText) findViewById(R.id.coneRadiusNumber);
        EditText coneHeightNumber = (EditText) findViewById(R.id.coneHeightNumber);
        TextView coneResultNumber = (TextView) findViewById(R.id.coneResultNumber);
        String coneRadiusNumberText = coneRadiusNumber.getText().toString();
        String coneHeightNumberText = coneHeightNumber.getText().toString();

        if(coneRadiusNumberText.isEmpty()||coneHeightNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double radius = Double.parseDouble(coneRadiusNumberText);
        double height = Double.parseDouble(coneHeightNumberText);
        double coneVolume = calculateConeVolume(radius, height);
        String coneVolumeString = form.format(coneVolume);
        coneResultNumber.setText(coneVolumeString);
    }

    public void displayDaysBetweenDatesResult(View view){
        EditText startDateBetween = (EditText) findViewById(R.id.startDateBetween);
        EditText endDateBetween = (EditText) findViewById(R.id.endDateBetween);
        CheckBox IfIncludeEndDay = (CheckBox) findViewById(R.id.ifIncludeEndDay);
        TextView betweenResultNumber = (TextView) findViewById(R.id.betweenResultNumber);
        String startDateBetweenText = startDateBetween.getText().toString();
        String endDateBetweenText = endDateBetween.getText().toString();

        if(startDateBetweenText.isEmpty()||endDateBetweenText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(startDateBetweenText);
            endDate = sdf.parse(endDateBetweenText);
        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "Provide a valid date in D/M/Y format.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        long resultBetweenDays = calculateDaysBetweenDates(startDate, endDate);
        if(IfIncludeEndDay.isChecked()){resultBetweenDays++;}
        String resultBetweenDaysString = Long.toString(resultBetweenDays);
        betweenResultNumber.setText(resultBetweenDaysString);
    }

    public void displayAddSubtractDate(View view){
        EditText startDateAdd = (EditText) findViewById(R.id.startDateAdd);
        EditText yearsAddDate = (EditText) findViewById(R.id.yearsAddDate);
        EditText monthsAddDate = (EditText) findViewById(R.id.monthsAddDate);
        EditText weeksAddDate = (EditText) findViewById(R.id.weeksAddDate);
        EditText daysAddDate = (EditText) findViewById(R.id.daysAddDate);
        RadioButton radioSubstractButton = findViewById(R.id.radioSubstractButton);
        TextView resultDateAdd = (TextView) findViewById(R.id.resultDateAdd);

        String startDateAddText = startDateAdd.getText().toString();
        String yearsAddDateText = yearsAddDate.getText().toString();
        String monthsAddDateText = monthsAddDate.getText().toString();
        String weeksAddDateText = weeksAddDate.getText().toString();
        String daysAddDateText = daysAddDate.getText().toString();

        if(startDateAddText.isEmpty()||yearsAddDateText.isEmpty()||monthsAddDateText.isEmpty()||weeksAddDateText.isEmpty()||daysAddDateText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date startDate = null;
        try {
            startDate = sdf.parse(startDateAddText);
        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "Provide a valid date in D/M/Y format.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        int years = Integer.parseInt(yearsAddDateText);
        int months = Integer.parseInt(monthsAddDateText);
        int weeks = Integer.parseInt(weeksAddDateText);
        int days = Integer.parseInt(daysAddDateText);

        if (radioSubstractButton.isChecked()){
            years = -years; months = -months; weeks = -weeks; days = -days;
        }

        Date resultAddDate = addSubtractDate(startDate, years, months, weeks, days);
        String resultDateAddString = sdf.format(resultAddDate);
        resultDateAdd.setText(resultDateAddString);
    }

    public void displayMetricBMIResult(View view){
        EditText weightMetricBMINumber = (EditText) findViewById(R.id.weightMetricBMINumber);
        EditText heightMetricBMINumber = (EditText) findViewById(R.id.heightMetricBMINumber);
        TextView resultMetricBMINumber = (TextView) findViewById(R.id.resultMetricBMINumber);

        String weightMetricBMINumberText = weightMetricBMINumber.getText().toString();
        String heightMetricBMINumberText = heightMetricBMINumber.getText().toString();

        if(weightMetricBMINumberText.isEmpty()||heightMetricBMINumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double weight = Double.parseDouble(weightMetricBMINumberText);
        double height = Double.parseDouble(heightMetricBMINumberText);

        double bmi = calculateBMI(weight, height, true);
        String bmiString = form.format(bmi);
        resultMetricBMINumber.setText(bmiString);
    }

    public void displayImperialBMIResult(View view){
        EditText weightImperialBMINumber = (EditText) findViewById(R.id.weightImperialBMINumber);
        EditText heightImperialBMINumber = (EditText) findViewById(R.id.heightImperialBMINumber);
        TextView resultImperialBMINumber = (TextView) findViewById(R.id.resultImperialBMINumber);

        String weightImperialBMINumberText = weightImperialBMINumber.getText().toString();
        String heightImperialBMINumberText = heightImperialBMINumber.getText().toString();

        if(weightImperialBMINumberText.isEmpty()||heightImperialBMINumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double weight = Double.parseDouble(weightImperialBMINumberText);
        double height = Double.parseDouble(heightImperialBMINumberText);

        double bmi = calculateBMI(weight, height, false);
        String bmiString = form.format(bmi);
        resultImperialBMINumber.setText(bmiString);
    }

    public void displayMetricIdealResult(View view){
        EditText heightIdealMetricNumber = (EditText) findViewById(R.id.heightIdealMetricNumber);
        TextView resultIdealMetricNumber = (TextView) findViewById(R.id.resultIdealMetricNumber);

        String resultIdealMetricNumberText = heightIdealMetricNumber.getText().toString();

        if(resultIdealMetricNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double height = Double.parseDouble(resultIdealMetricNumberText);

        double[] weight = calculateIdealWeight(height, true);
        String weightString = form1.format(weight[0]) + "kg to " + form1.format(weight[1]) + "kg";
        resultIdealMetricNumber.setText(weightString);
    }

    public void displayImperialIdealResult(View view){
        EditText heightIdealImperialNumber = (EditText) findViewById(R.id.heightIdealImperialNumber);
        TextView resultIdealImperialNumber = (TextView) findViewById(R.id.resultIdealImperialNumber);

        String resultIdealImperialNumberText = heightIdealImperialNumber.getText().toString();

        if(resultIdealImperialNumberText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double height = Double.parseDouble(resultIdealImperialNumberText);

        double[] weight = calculateIdealWeight(height, false);
        String weightString = form1.format(weight[0]) + "lbs to " + form1.format(weight[1]) + "lbs";
        resultIdealImperialNumber.setText(weightString);
    }

    public void displayStatisticsValues (View view){
        EditText valuesStatistics = (EditText) findViewById(R.id.valuesStatistics);

        TextView countStatisticsNumber = (TextView) findViewById(R.id.countStatisticsNumber);
        TextView sumStatisticsNumber = (TextView) findViewById(R.id.sumStatisticsNumber);
        TextView meanStatisticsNumber = (TextView) findViewById(R.id.meanStatisticsNumber);
        TextView medianStatisticsNumber = (TextView) findViewById(R.id.medianStatisticsNumber);
        TextView largestStatisticsNumber = (TextView) findViewById(R.id.largestStatisticsNumber);
        TextView smallestStatisticsNumber = (TextView) findViewById(R.id.smallestStatisticsNumber);
        TextView rangeStatisticsNumber = (TextView) findViewById(R.id.rangeStatisticsNumber);
        TextView geometricStatisticsNumber = (TextView) findViewById(R.id.geometricStatisticsNumber);
        TextView sdStatisticsNumber = (TextView) findViewById(R.id.sdStatisticsNumber);
        TextView varianceStatisticsNumber = (TextView) findViewById(R.id.varianceStatisticsNumber);
        TextView samplesdStatisticsNumber = (TextView) findViewById(R.id.samplesdStatisticsNumber);
        TextView sampleVarianceStatisticsNumber = (TextView) findViewById(R.id.sampleVarianceStatisticsNumber);

        String valuesStatisticsText = valuesStatistics.getText().toString();

        if(valuesStatisticsText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        String[] valuesString = valuesStatisticsText.split(",");
        double[] values = new double[valuesString.length];

        try{
            for (int i = 0; i < valuesString.length; i++) {
                values[i] = Double.parseDouble(valuesString[i]);
            }
        } catch (Exception e){
            Toast toast = Toast.makeText(this, "Input numerical values separated by comma", Toast.LENGTH_SHORT);
            toast.show();
        }

        countStatisticsNumber.setText(Integer.toString(calculateCount(values)));
        sumStatisticsNumber.setText(form.format(calculateSum(values)));
        meanStatisticsNumber.setText(form.format(calculateAverage(values)));
        medianStatisticsNumber.setText(form.format(calculateMedian(values)));
        largestStatisticsNumber.setText(form.format(calculateLargest(values)));
        smallestStatisticsNumber.setText(form.format(calculateSmallest(values)));
        rangeStatisticsNumber.setText(form.format(calculateRange(values)));
        geometricStatisticsNumber.setText(form.format(calculateGeometricMean(values)));
        sdStatisticsNumber.setText(form.format(calculatePopulationStandardDeviation(values)));
        varianceStatisticsNumber.setText(form.format(calculateVariance(values)));
        samplesdStatisticsNumber.setText(form.format(calculateSampleStandardDeviation(values)));
        sampleVarianceStatisticsNumber.setText(form.format(calculateSampleVariance(values)));
    }

    public void displayProbabilityValues (View view){
        EditText probabilityANumber = (EditText) findViewById(R.id.probabilityANumber);
        EditText probabilityBNumber = (EditText) findViewById(R.id.probabilityBNumber);

        TextView notANumber = (TextView) findViewById(R.id.notANumber);
        TextView notBNumber = (TextView) findViewById(R.id.notBNumber);
        TextView AandBNumber = (TextView) findViewById(R.id.AandBNumber);
        TextView AorBNumber = (TextView) findViewById(R.id.AorBNumber);
        TextView AorBNotBothNumber = (TextView) findViewById(R.id.AorBNotBothNumber);
        TextView AnorBNumber = (TextView) findViewById(R.id.AnorBNumber);

        String probabilityA = probabilityANumber.getText().toString();
        String probabilityB = probabilityBNumber.getText().toString();

        if(probabilityA.isEmpty()||probabilityB.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double notA = 1 - Double.parseDouble(probabilityA);
        double notB = 1 - Double.parseDouble(probabilityB);
        double AandB = Double.parseDouble(probabilityA) * Double.parseDouble(probabilityB);
        double AorB = Double.parseDouble(probabilityA) + Double.parseDouble(probabilityB) - AandB;
        double AorBNotBoth = Double.parseDouble(probabilityA) + Double.parseDouble(probabilityB) - (2 * AandB);
        double AnorB = 1 - AorB;

        notANumber.setText(form.format(notA));
        notBNumber.setText(form.format(notB));
        AandBNumber.setText(form.format(AandB));
        AorBNumber.setText(form.format(AorB));
        AorBNotBothNumber.setText(form.format(AorBNotBoth));
        AnorBNumber.setText(form.format(AnorB));
    }

    public void displayPermutationValues (View view){
        EditText setNumber = (EditText) findViewById(R.id.setNumber);
        EditText subSetNumber = (EditText) findViewById(R.id.subSetNumber);

        TextView permutationsResult = (TextView) findViewById(R.id.permutationsResult);
        TextView combinationsResult = (TextView) findViewById(R.id.combinationsResult);

        String set = setNumber.getText().toString();
        String subSet = subSetNumber.getText().toString();

        if(set.isEmpty()||subSet.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        int n = Integer.parseInt(set);
        int r = Integer.parseInt(subSet);

        int permutations = calculatePermutation(n, r);
        int combinations = calculateCombination(n, r);

        permutationsResult.setText(Integer.toString(permutations));
        combinationsResult.setText(Integer.toString(combinations));
    }

    public void displayCurrentValues (View view){
        EditText voltage = (EditText) findViewById(R.id.voltageCurrentNumber);
        EditText resistance = (EditText) findViewById(R.id.resistanceCurrentNumber);

        TextView currentResultNumber = (TextView) findViewById(R.id.currentResultNumber);

        String vText = voltage.getText().toString();
        String rText = resistance.getText().toString();


        if(vText.isEmpty()||rText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double v = Double.parseDouble(vText);
        double r = Double.parseDouble(rText);

        double i = v/r;

        currentResultNumber.setText(form.format(i));
    }

    public void displayResistanceValues (View view){
        EditText voltage = (EditText) findViewById(R.id.voltageResistanceNumber);
        EditText current = (EditText) findViewById(R.id.currentResistanceNumber);

        TextView resistanceResultNumber = (TextView) findViewById(R.id.resistanceResultNumber);

        String vText = voltage.getText().toString();
        String iText = current.getText().toString();


        if(vText.isEmpty()||iText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double v = Double.parseDouble(voltage.getText().toString());
        double i = Double.parseDouble(current.getText().toString());

        double r = v/i;

        resistanceResultNumber.setText(form.format(r));
    }

    public void displayVoltageValues (View view){
        EditText current = (EditText) findViewById(R.id.currentVoltageNumber);
        EditText resistance = (EditText) findViewById(R.id.resistanceVoltageNumber);

        TextView voltageResultNumber = (TextView) findViewById(R.id.voltageResultNumber);

        String rText = resistance.getText().toString();
        String iText = current.getText().toString();

        if(rText.isEmpty()||iText.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double i = Double.parseDouble(current.getText().toString());
        double r = Double.parseDouble(resistance.getText().toString());

        double v = r*i;

        voltageResultNumber.setText(form.format(v));
    }

    public void displayCircleArea(View view){
        EditText circleAreaSideNumber = (EditText) findViewById(R.id.circleRadiusAreaNumber);

        TextView circleAreaResultNumber = (TextView) findViewById(R.id.circleAreaResultNumber);
        String circleAreaSide = circleAreaSideNumber.getText().toString();

        if(circleAreaSide.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double r = Double.parseDouble(circleAreaSide);
        double v = Math.PI * r * r;

        circleAreaResultNumber.setText(form.format(v));
    }

    public void displayEllipseArea(View view){
        EditText ellipseAreaMajorNumber = (EditText) findViewById(R.id.ellipseAreaMajorNumber);
        EditText ellipseAreaMinorNumber = (EditText) findViewById(R.id.ellipseAreaMinorNumber);

        TextView ellipseAreaResultNumber = (TextView) findViewById(R.id.ellipseAreaResultNumber);

        String ellipseAreaMajor = ellipseAreaMajorNumber.getText().toString();
        String ellipseAreaMinor = ellipseAreaMinorNumber.getText().toString();

        if(ellipseAreaMajor.isEmpty()||ellipseAreaMinor.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double major = Double.parseDouble(ellipseAreaMajor);
        double minor = Double.parseDouble(ellipseAreaMinor);

        double v = Math.PI * major * minor;

        ellipseAreaResultNumber.setText(form.format(v));
    }

    public void displayParallelogramArea(View view){
        EditText parallelogramAreaHeightNumber = (EditText) findViewById(R.id.parallelogramAreaHeightNumber);
        EditText parallelogramAreaBaseNumber = (EditText) findViewById(R.id.parallelogramAreaBaseNumber);

        TextView parallelogramAreaHeightResultNumber = (TextView) findViewById(R.id.parallelogramAreaHeightResultNumber);

        String parallelogramAreaHeight = parallelogramAreaHeightNumber.getText().toString();
        String parallelogramAreaBase = parallelogramAreaBaseNumber.getText().toString();

        if(parallelogramAreaHeight.isEmpty()||parallelogramAreaBase.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double h = Double.parseDouble(parallelogramAreaHeight);
        double b = Double.parseDouble(parallelogramAreaBase);

        double v = h * b;

        parallelogramAreaHeightResultNumber.setText(form.format(v));
    }

    public void displayRectangleArea(View view){
        EditText rectangleAreaSide1Number = (EditText) findViewById(R.id.rectangleAreaSide1Number);
        EditText rectangleAreaSide2Number = (EditText) findViewById(R.id.rectangleAreaSide2Number);

        TextView triangleAreaEdgeResultNumber = (TextView) findViewById(R.id.squareAreaEdgeResultNumber);

        String rectangleAreaSide1 = rectangleAreaSide1Number.getText().toString();
        String rectangleAreaSide2 = rectangleAreaSide2Number.getText().toString();

        if(rectangleAreaSide1.isEmpty()||rectangleAreaSide2.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double a = Double.parseDouble(rectangleAreaSide1);
        double b = Double.parseDouble(rectangleAreaSide2);

        double v = a * b;

        triangleAreaEdgeResultNumber.setText(form.format(v));
    }

    public void displaySquareArea(View view){
        EditText squareAreaSideNumber = (EditText) findViewById(R.id.squareAreaSideNumber);

        TextView squareAreaEdgeResultNumber = (TextView) findViewById(R.id.squareAreaEdgeResultNumber);
        String squareAreaSide = squareAreaSideNumber.getText().toString();

        if(squareAreaSide.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double a = Double.parseDouble(squareAreaSide);

        double v = a * a;

        squareAreaEdgeResultNumber.setText(form.format(v));
    }

    public void displayTrapezoidArea(View view){
        EditText trapezoidAreaHeightNumber = (EditText) findViewById(R.id.trapezoidAreaHeightNumber);
        EditText trapezoidAreaBase1Number = (EditText) findViewById(R.id.trapezoidAreaBase1Number);
        EditText trapezoidAreaBase2Number = (EditText) findViewById(R.id.trapezoidAreaBase2Number);

        TextView trapezoidAreaResultNumber = (TextView) findViewById(R.id.trapezoidAreaResultNumber);

        String trapezoidAreaHeight = trapezoidAreaHeightNumber.getText().toString();
        String trapezoidAreaBase1 = trapezoidAreaBase1Number.getText().toString();
        String trapezoidAreaBase2 = trapezoidAreaBase2Number.getText().toString();

        if(trapezoidAreaHeight.isEmpty()||trapezoidAreaBase1.isEmpty()||trapezoidAreaBase2.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double h = Double.parseDouble(trapezoidAreaHeight);
        double a = Double.parseDouble(trapezoidAreaBase1);
        double b = Double.parseDouble(trapezoidAreaBase2);
        double v = ((a + b) / 2) * h;

        trapezoidAreaResultNumber.setText(form.format(v));
    }

    public void displayTriangleEdgeArea(View view){
        EditText triangleAreaEdge1Number = (EditText) findViewById(R.id.triangleAreaEdge1Number);
        EditText triangleAreaEdge2Number = (EditText) findViewById(R.id.triangleAreaEdge2Number);
        EditText triangleAreaEdge3Number = (EditText) findViewById(R.id.triangleAreaEdge3Number);

        TextView triangleAreaEdgeResultNumber = (TextView) findViewById(R.id.triangleAreaEdgeResultNumber);

        String triangleAreaEdge1 = triangleAreaEdge1Number.getText().toString();
        String triangleAreaEdge2 = triangleAreaEdge2Number.getText().toString();
        String triangleAreaEdge3 = triangleAreaEdge3Number.getText().toString();

        if(triangleAreaEdge1.isEmpty()||triangleAreaEdge2.isEmpty()||triangleAreaEdge3.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double a = Double.parseDouble(triangleAreaEdge1);
        double b = Double.parseDouble(triangleAreaEdge2);
        double c = Double.parseDouble(triangleAreaEdge3);
        double s = (a + b + c) / 2;

        double v = sqrt(s * (s-a) * (s-b) * (s-c));

        triangleAreaEdgeResultNumber.setText(form.format(v));
    }

    public void displayTriangleHeightArea(View view){
        EditText triangleAreaHeightBaseNumber = (EditText) findViewById(R.id.triangleAreaHeightBaseNumber);
        EditText triangleAreaHeightHeightNumber = (EditText) findViewById(R.id.triangleAreaHeightHeightNumber);

        TextView triangleAreaHeightResultNumber = (TextView) findViewById(R.id.triangleAreaHeightResultNumber);
        String triangleAreaHeightBase = triangleAreaHeightBaseNumber.getText().toString();
        String triangleAreaHeightHeight = triangleAreaHeightHeightNumber.getText().toString();

        if(triangleAreaHeightBase.isEmpty()||triangleAreaHeightHeight.isEmpty()){
            Toast toast = Toast.makeText(this, "An input is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        double b = Double.parseDouble(triangleAreaHeightBase);
        double h = Double.parseDouble(triangleAreaHeightHeight);

        double v = (h * b) / 2;

        triangleAreaHeightResultNumber.setText(form.format(v));
    }

    public void AddFavorites(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ToggleButton toggleButton = (ToggleButton) view;

        String buttonResourceName = getResources().getResourceName(toggleButton.getId());
        String buttomFullTitle = buttonResourceName.substring(buttonResourceName.lastIndexOf("/") + 1);
        String title = buttomFullTitle.substring(0, buttomFullTitle.length() - 12);

        if (toggleButton.isChecked()) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_star_selected));
            editor.putInt(title, 1);
        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_star_unselected));
            editor.remove(title);
        }
        editor.commit();
    }
}