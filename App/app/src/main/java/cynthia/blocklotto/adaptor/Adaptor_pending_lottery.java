package cynthia.blocklotto.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adaptor_pending_lottery extends RecyclerView.Adapter<Adaptor_pending_lottery.ViewHolder>{

    private List<PendingLottery> listPendingLottery;
    private TextView accumulatedTextAux;
    private TextView accumulatedAux;

    public Adaptor_pending_lottery(List<PendingLottery> listPendingLottery){
        this.listPendingLottery = listPendingLottery;
    }

    public List<PendingLottery> getListPendingLottery(){ return listPendingLottery;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);
        accumulatedTextAux = (TextView) view.findViewById(R.id.accumulatedPendingText);
        accumulatedAux = (TextView) view.findViewById(R.id.accumulatedPending);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listPendingLottery.get(position).getName());
        holder.date.setText(listPendingLottery.get(position).getDate());
        holder.photo.setImageResource(listPendingLottery.get(position).getPhoto());
        holder.numTickets.setText(listPendingLottery.get(position).getAmountTicket()+"");
        controlAccumulated(holder, position);
    }

    private void controlAccumulated(ViewHolder holder, int position){
        if(listPendingLottery.get(position).getAccumulated() == null){
            accumulatedAux.setVisibility(View.INVISIBLE);
            accumulatedTextAux.setVisibility(View.INVISIBLE);
        }else{
            accumulatedAux.setVisibility(View.VISIBLE);
            accumulatedTextAux.setVisibility(View.VISIBLE);
            holder.accumulated.setText(listPendingLottery.get(position).getAccumulated()+"");
        }
    }
    @Override
    public int getItemCount() {
        return listPendingLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, numTickets, accumulated;
        private ImageView photo;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.namePendingLottery);
            date=iterView.findViewById(R.id.datePendingLottery);
            photo= iterView.findViewById(R.id.imgPendingLottery);
            numTickets = iterView.findViewById(R.id.numTicketPending);
            accumulated= iterView.findViewById(R.id.accumulatedPending);
        }
    }
}
