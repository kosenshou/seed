package com.eleartech.speed.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleartech.speed.R;

import java.util.ArrayList;

/**
 * Created by junverarcayna on 22/10/2017.
 */

public class StatusAdapter extends ArrayAdapter<Status> {

    public LayoutInflater inflater;

    public StatusAdapter(Context context, Status[] statuses) {
        super(context, R.layout.layout_status, statuses);
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent) {
        inflater = LayoutInflater.from(getContext());
        View layoutStatusView = inflater.inflate(R.layout.layout_status, parent, false);

        Status status = getItem(position);

        TextView textView = (TextView) layoutStatusView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) layoutStatusView.findViewById(R.id.imageView);

        if (status == null)
            return layoutStatusView;

        textView.setText(status.name);
        if (status.isComplete)
            imageView.setVisibility(View.VISIBLE);
        else
            imageView.setVisibility(View.INVISIBLE);

        return layoutStatusView;
    }

}

