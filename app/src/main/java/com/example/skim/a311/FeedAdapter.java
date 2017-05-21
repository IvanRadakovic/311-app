package com.example.skim.a311;

/**
 * Created by SriramHariharan on 5/20/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by SriramHariharan on 3/11/16.
 */
public class FeedAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Crime> courses;
    private static LayoutInflater inflater = null;

    public FeedAdapter (Context contxt,ArrayList<Crime> crs) {
        courses = crs;
        context = contxt;
        inflater = (LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView time;
        TextView date;
        TextView location;
        TextView crime;
        ImageView im;
        LinearLayout color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.rowlayout, null);
        holder.time=(TextView) rowView.findViewById(R.id.time);
        holder.im = (ImageView)rowView.findViewById(R.id.iconn);
        holder.date=(TextView) rowView.findViewById(R.id.date);
        holder.location = (TextView) rowView.findViewById(R.id.location);
        holder.crime = (TextView) rowView.findViewById(R.id.crime);


        holder.time.setText(courses.get(position).getTime());
        holder.date.setText(courses.get(position).getDate());
        holder.location.setText(courses.get(position).getLocation());
        String s = courses.get(position).getCrime();
        holder.crime.setText(s);

        if(s.equals("Rape")){
            holder.im.setImageResource(R.drawable.ic_whistle);

        }
        else if(s.equals("Aggravated Assault")){
            holder.im.setImageResource(R.drawable.ic_fist);

        }
        else if(s.equals("Robbery")){
            holder.im.setImageResource(R.drawable.ic_robbery);

        }
        else if(s.equals("Burglar")){
            holder.im.setImageResource(R.drawable.ic_home_black_24dp);

        }
        else if(s.equals("Auto Theft")){
            holder.im.setImageResource(R.drawable.ic_car_robbery);

        }
        else if(s.equals("Murder")){
            holder.im.setImageResource(R.drawable.ic_assault);

        }
        else if(s.equals("Theft")){
            holder.im.setImageResource(R.drawable.ic_money_bag);

        }

        return rowView;
    }


}
