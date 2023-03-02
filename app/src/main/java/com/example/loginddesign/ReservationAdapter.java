package com.example.loginddesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    List<ResrvationModel> postList;
    public ReservationAdapter(Context context, List<ResrvationModel> postList) {
        this.postList=postList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_row_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.FlightID.setText(Integer.toString(postList.get(position).getFlightID()));
        holder.FlightName.setText(postList.get(position).getFlightName());
        holder.Cost.setText(Integer.toString(postList.get(position).getCost()));
        holder.From.setText("From:"+postList.get(position).getFrom());
        holder.To.setText("To:"+postList.get(position).getTo());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView FlightID,FlightName,Cost,UserNameText,From,To;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FlightID=itemView.findViewById(R.id.FlightID);
            FlightName=itemView.findViewById(R.id.FlightName);
            Cost=itemView.findViewById(R.id.Cost);
            button=itemView.findViewById(R.id.idHolder);
            From=itemView.findViewById(R.id.From);
            To=itemView.findViewById(R.id.To);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), postList.size());
                }
            });

        }

    }

}