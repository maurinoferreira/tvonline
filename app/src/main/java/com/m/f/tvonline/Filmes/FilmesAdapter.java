package com.m.f.tvonline.Filmes;



/**
 * Created by mauri on 25/11/2018.
 */


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.m.f.tvonline.R;

import java.util.List;

import static java.util.Locale.filter;

public class FilmesAdapter extends BaseAdapter  {
    private Context context;
    private List<Filmes> list;


    public FilmesAdapter(Context context, List<Filmes> list) {
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
        com.m.f.tvonline.Filmes.ViewHolder holder = null;
        if (view == null) {
            holder = new com.m.f.tvonline.Filmes.ViewHolder();

            view = View.inflate(context, R.layout.movie_item, null);

            holder.movie_name = (TextView) view.findViewById(R.id.movie_name);
            view.setTag(holder);
        } else {
            holder = (com.m.f.tvonline.Filmes.ViewHolder) view.getTag();
        }

        holder.movie_name.setText(list.get(i).movie_name.toString());

//        holder.position.setText(i+1+"");

        return view;
    }




}


class ViewHolder {
    TextView movie_name;

}


