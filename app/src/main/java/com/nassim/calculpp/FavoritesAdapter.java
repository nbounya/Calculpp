package com.nassim.calculpp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.nassim.calculpp.DisplayCalculatorActivity.BACK_REFRESH;

public class FavoritesAdapter extends ArrayAdapter<String>{
    private final Activity favContext;
    private final String[] calculatorDescription;
    private final Integer[] calculatorImage;
    private final String[] calculatorName;

    public FavoritesAdapter(Activity favcontext,
                            String[] CalculatorDescription, Integer[] CalculatorImage, String[] calculatorName) {

        super(favcontext, R.layout.fav_line_view, CalculatorDescription);
        this.favContext = favcontext;
        this.calculatorImage = CalculatorImage;
        this.calculatorDescription = CalculatorDescription;
        this.calculatorName = calculatorName;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = favContext.getLayoutInflater();
        View favrowView = inflater.inflate(R.layout.fav_line_view, null, true);
        TextView favtxtTitle = (TextView) favrowView.findViewById(R.id.favfirstLine);
        TextView favIdText = (TextView) favrowView.findViewById(R.id.favIdText);
        ImageView imageView = (ImageView) favrowView.findViewById(R.id.favicon);
        ToggleButton favListToggleButton = (ToggleButton) favrowView.findViewById(R.id.favListToggleButton);
        SharedPreferences sharedPreferences = favContext.getSharedPreferences(
                "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        favtxtTitle.setText(calculatorDescription[position]);
        imageView.setImageResource(calculatorImage[position]);
        favIdText.setText(calculatorName[position]);
        favListToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(calculatorName[position].split(",")[1]);
                editor.commit();
                Intent displayCalculator = new Intent(favContext, MainActivity.class);
                displayCalculator.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                displayCalculator.putExtra(BACK_REFRESH, "fav");
                favContext.startActivity(displayCalculator);
            }
        });

        return favrowView;
    }
}
