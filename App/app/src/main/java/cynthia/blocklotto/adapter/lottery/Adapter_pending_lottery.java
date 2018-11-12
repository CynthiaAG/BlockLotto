package cynthia.blocklotto.adapter.lottery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cynthia.blocklotto.action.lottery.InfoLottery;
import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adapter_pending_lottery extends RecyclerView.Adapter<Adapter_pending_lottery.ViewHolder>{

    private List<PendingLottery> listPendingLottery;
    private TextView accumulatedTextAux;
    private TextView accumulatedAux;
    private Button info;

    public Adapter_pending_lottery(List<PendingLottery> listPendingLottery){
        this.listPendingLottery = listPendingLottery;
    }

    public List<PendingLottery> getListPendingLottery(){ return listPendingLottery;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_lottery, parent, false);
        final ViewHolder viewHolder= new ViewHolder(view);

        info= (Button) view.findViewById(R.id.infoPendingButton);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(c , InfoLottery.class);
                final int position = viewHolder.getAdapterPosition();
                sendElements(intent, position, "Pending");
                c.startActivity(intent);
            }
        });
        return viewHolder;
    }

    private void sendElements(Intent intent, int position, String type){
        intent.putExtra("type", type);
        intent.putExtra("id", listPendingLottery.get(position).getId());
        intent.putExtra("date", listPendingLottery.get(position).getDate());
        intent.putExtra("name", listPendingLottery.get(position).getName());
        intent.putExtra("info", listPendingLottery.get(position).getInformation());
        intent.putExtra("award", listPendingLottery.get(position).getAward());
        intent.putExtra("priceBadge", listPendingLottery.get(position).getPriceBadge());
        intent.putExtra("amount",listPendingLottery.get(position).getAmountTicket());
        intent.putExtra("priceFinal", listPendingLottery.get(position).getPriceTotal());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listPendingLottery.get(position).getName());
        holder.date.setText(listPendingLottery.get(position).getDate());
        holder.photo.setImageResource(listPendingLottery.get(position).getPhoto());
        holder.numTickets.setText(listPendingLottery.get(position).getAmountTicket()+"");
        holder.award.setText(listPendingLottery.get(position).getAward());
        //controlAccumulated(holder, position);
    }

    /*private void controlAccumulated(ViewHolder holder, int position){
        if(listPendingLottery.get(position).getAccumulated() == null){
            accumulatedAux.setVisibility(View.INVISIBLE);
            accumulatedTextAux.setVisibility(View.INVISIBLE);
        }else{
            accumulatedAux.setVisibility(View.VISIBLE);
            accumulatedTextAux.setVisibility(View.VISIBLE);
            holder.accumulated.setText(listPendingLottery.get(position).getAccumulated()+"");
        }
    }*/
    @Override
    public int getItemCount() {
        return listPendingLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, numTickets, award;
        private ImageView photo;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.namePendingLottery);
            date=iterView.findViewById(R.id.datePendingLottery);
            photo= iterView.findViewById(R.id.imgPendingLottery);
            numTickets = iterView.findViewById(R.id.numTicketPending);
            award= iterView.findViewById(R.id.awardPending);
        }
    }
}
