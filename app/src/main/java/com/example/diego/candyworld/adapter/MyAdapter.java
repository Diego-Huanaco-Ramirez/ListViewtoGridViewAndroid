package com.example.diego.candyworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diego.candyworld.R;

import java.io.Serializable;
import java.util.List;
public class MyAdapter extends BaseAdapter  {
    private Context context;
    private int layout;
    private List<String> nombres;
    private List<String> origen;

    public MyAdapter(Context context, int layout, List<String> nombres,List<String> origen){
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
        this.origen = origen;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setNombres(List<String> nombres) {
        this.nombres = nombres;
    }

    public void setOrigen(List<String> origen) {
        this.origen = origen;
    }

    @Override
    public int getCount() {
        return this.nombres.size();
    }
    @Override
    public Object getItem(int position) {
        return this.nombres.get(position);
    }
    @Override
    public long getItemId(int id) {
        return id;
    }

    public Context getContext() {
        return context;
    }

    public int getLayout() {
        return layout;
    }

    public List<String> getNombres() {
        return nombres;
    }

    public List<String> getOrigen() {
        return origen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Patron View holder
        ViewHolder holder;

        if(convertView == null){
            //Inflamos la vista que nos ha llegado a nuestro layout
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();

            //Referencias el elemento a modificar y lo rellenamos
            holder.nametextView = (TextView) convertView.findViewById(R.id.textView_nombre);
            holder.origintextView = (TextView) convertView.findViewById(R.id.textView_origen);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Nos traemos el valor actual dependiente de la posición
        String nombreActual = nombres.get(position);
        String origenActual = origen.get(position);

        //Referencias el elemento a modificar y lo rellenamos

        holder.nametextView.setText(nombreActual);
        holder.origintextView.setText(origenActual);
        return convertView;
    }

    static class ViewHolder {
        public TextView nametextView;
        public TextView origintextView;
    }
}
