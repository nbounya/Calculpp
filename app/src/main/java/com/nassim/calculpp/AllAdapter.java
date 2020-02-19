package com.nassim.calculpp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AllAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] secondLine;
    private final String[] firstLine;
    private final int[] imageId;

    public AllAdapter(Activity context,
                      String[] firstLine, String[] secondLine, int[] imageId) {
        super(context, R.layout.line_view, firstLine);
        this.context = context;
        this.imageId = imageId;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.line_view, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.firstLine);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(firstLine[position]);
        txtDesc.setText(secondLine[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}