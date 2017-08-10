package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by setha on 04-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<EarthQuake> earthquakes = new ArrayList<>();
    private Context context;
    public TextView earthQuakeMagnitude;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView earthQuakePrimaryLocation;
        public TextView earthQuakeOffsetLocation;
        public TextView earthQuakeDate;
        public TextView earthQuakeTime;
        private LinearLayout singleQuake;

        public ViewHolder(View itemView) {
            super(itemView);
            earthQuakeMagnitude = (TextView)itemView.findViewById(R.id.text_quake_magnitude);
            earthQuakePrimaryLocation = (TextView)itemView.findViewById(R.id.text_quake_primary_location);
            earthQuakeOffsetLocation = (TextView)itemView.findViewById(R.id.text_quake_offset_location);
            earthQuakeDate = (TextView)itemView.findViewById(R.id.text_quake_date);
            earthQuakeTime = (TextView)itemView.findViewById(R.id.text_quake_time);
            singleQuake = (LinearLayout)itemView.findViewById(R.id.layout_single_quake);
        }
    }

    public MyAdapter(List<EarthQuake> earthquakes){
        this.earthquakes = earthquakes;
    }


    //this method will be called when viewHolder is created
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    //this method will bind the data to the viewHolder
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        EarthQuake earthQuake = earthquakes.get(position);

        //handling Intent to open a webpage containing detail information about the earthquake
        final String quake_Url = earthQuake.getmUrl();
        holder.singleQuake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse(quake_Url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                context.startActivity(intent);
            }
        });

        //code to show magnitude in ("0.0") formated form
        earthQuakeMagnitude.setText(formatMagnitude(earthQuake.getmMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable)earthQuakeMagnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthQuake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //code to show the offsetLocation and primaryLocation
        String offsetLocation = "";
        String primaryLocation = "";
        String location = earthQuake.getmLocation();
        int index = formatLocation(location);
        if (index != -1){
            offsetLocation = location.substring(0,index+2);
            primaryLocation = location.substring(index+3);
        }
        else {
            offsetLocation = "Near the";
            primaryLocation = location;
        }
        holder.earthQuakePrimaryLocation.setText(primaryLocation);
        holder.earthQuakeOffsetLocation.setText(offsetLocation);

        //code to show date and time
        Date dateObject = new Date(earthQuake.getmDate());
        holder.earthQuakeDate.setText(formatDate(dateObject));
        holder.earthQuakeTime.setText(formatTime(dateObject));
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the index position from which string location to be divided in two substring
     * so that we can get offsetLocation and primaryLocation.
     */
    private int formatLocation(String location) {
        return location.indexOf("of");
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(context,magnitudeColorResourceId);
    }

}
