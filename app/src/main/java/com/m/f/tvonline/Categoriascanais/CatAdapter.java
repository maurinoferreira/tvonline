package com.m.f.tvonline.Categoriascanais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.m.f.tvonline.R;

import java.util.List;

/**
 * Created by mauri on 23/01/2019.
 */

public class CatAdapter extends BaseAdapter {

    private Context context;
    private List<Categorias> list;

    public CatAdapter(Context context, List<Categorias> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (view == null) {
            // inflate UI from XML file
            view = inflater.inflate(R.layout.categorias, viewGroup, false);

            // get all UI view
            holder = new ViewHolder(view);
            holder.cat_name = (TextView) view.findViewById(R.id.cat_name);
            // set tag for holder
            view.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) view.getTag();
        }

        holder.cat_name.setText(list.get(i).cat_name);

        //get first letter of each String item
        String firstLetter = String.valueOf(list.get(i).cat_name);
        char f = firstLetter.charAt(0);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(i));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(f), color); // radius in px

        holder.imageView.setImageDrawable(drawable);



        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView cat_name;

        public ViewHolder(View v) {
            imageView = (ImageView) v.findViewById(R.id.cat_image);

        }
    }



}
