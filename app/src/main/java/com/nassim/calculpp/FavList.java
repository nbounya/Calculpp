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

import static com.nassim.calculpp.DisplayAllInputsActivity.BACK_REFRESH;

public class FavList extends ArrayAdapter<String>{
    public static final String UNFAV_REFRESH = "com.example.myfirstapp.UNFAV_REFRESH";
    private final Activity favcontext;
    private final String[] favfirstLine;
    private final Integer[] favimageId;
    private final String[] favId;

    public FavList(Activity favcontext,
                   String[] favfirstLine, Integer[] favimageId, String[] favId) {

        super(favcontext, R.layout.fav_line_view, favfirstLine);
        this.favcontext = favcontext;
        this.favimageId = favimageId;
        this.favfirstLine = favfirstLine;
        this.favId = favId;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = favcontext.getLayoutInflater();
        View favrowView = inflater.inflate(R.layout.fav_line_view, null, true);
        TextView favtxtTitle = (TextView) favrowView.findViewById(R.id.favfirstLine);
        TextView favIdText = (TextView) favrowView.findViewById(R.id.favIdText);
        ImageView imageView = (ImageView) favrowView.findViewById(R.id.favicon);
        ToggleButton favListToggleButton = (ToggleButton) favrowView.findViewById(R.id.favListToggleButton);
        SharedPreferences sharedPreferences = favcontext.getSharedPreferences(
                "com.example.myapp.FAVORITESLIST", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        favtxtTitle.setText(favfirstLine[position]);
        imageView.setImageResource(favimageId[position]);
        favIdText.setText(favId[position]);
        favListToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(favId[position].split(",")[1]);
                editor.commit();
                Intent a = new Intent(favcontext, MainActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                a.putExtra(BACK_REFRESH, "fav");
                favcontext.startActivity(a);
            }
        });

        return favrowView;
    }
}
