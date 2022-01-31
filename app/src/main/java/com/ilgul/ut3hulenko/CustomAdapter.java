package com.ilgul.ut3hulenko;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    ArrayList<String> centrosNombres;
    ArrayList<String> direccionIds;
    ArrayList<String> centrosNumeros;
    ArrayList<String> centrosPoblacion;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> centrosNombres, ArrayList<String> direccionIds, ArrayList<String> centrosPoblacion, ArrayList<String> mobileNumbers) {
        this.context = context;
        this.centrosNombres = centrosNombres;
        this.direccionIds = direccionIds;
        this.centrosNumeros = mobileNumbers;
        this.centrosPoblacion = centrosPoblacion;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_card_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // set the data in items
        holder.nombre.setText(centrosNombres.get(position));
        holder.direccion.setText(direccionIds.get(position));
        holder.centroNo.setText(centrosNumeros.get(position));
        holder.centroPob.setText(centrosPoblacion.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, centrosNumeros.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return centrosNombres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, direccion, centroPob, centroNo;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            nombre = (TextView) itemView.findViewById(R.id.NOMBRE);
            direccion = (TextView) itemView.findViewById(R.id.DIRECCIÓN);
            centroNo = (TextView) itemView.findViewById(R.id.TELÉFONO);
            centroPob= (TextView) itemView.findViewById(R.id.LOCALIDAD);
        }
    }
}
