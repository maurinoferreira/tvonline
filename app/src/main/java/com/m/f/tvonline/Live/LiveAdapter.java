package com.m.f.tvonline.Live;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.m.f.tvonline.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LiveAdapter extends ArrayAdapter<Live> {
    private Context context;
    private List<Live> list;
    ArrayList<Live> arrayList;


    public LiveAdapter(Context context, List<Live> list ) {
        super(context, R.layout.live_item, list);
        this.context = context;
        this.list = list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(list);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Live getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.live_item, parent, false);

            // get all UI view
            holder = new ViewHolder(convertView);
            holder.live_name = (TextView) convertView.findViewById(R.id.live_name);


            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.live_name.setText(list.get(position).live_name);

        //get first letter of each String item
        String firstLetter = String.valueOf(list.get(position).live_name);
        char f = firstLetter.charAt(0);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(f), color); // radius in px

        holder.imageView.setImageDrawable(drawable);


        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView live_name;
        ImageView favoriteImg;

        public ViewHolder(View v) {
            imageView = (ImageView) v.findViewById(R.id.live_image);

        }
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length()==0){
            list.addAll(arrayList);
        }
        else {
            for (Live model : arrayList){
                if (model.getLive_name().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


}




/**
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();

            view = View.inflate(context, R.layout.live_item, null);

            holder.live_name = (TextView) view.findViewById(R.id.live_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.live_name.setText(list.get(i).live_name.toString());

//        holder.position.setText(i+1+"");

        return view;
    }




}


    class ViewHolder {
        TextView live_name;
        ImageView imageView;

        public Holder(View v) {
            imageView = (ImageView) v.findViewById(R.id.live_image);

        }

    }
**/

