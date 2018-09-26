package cynthia.blocklotto.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cynthia.blocklotto.lottery.CelebratedLottery;
import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adaptor_celebrated_lottery extends RecyclerView.Adapter<Adaptor_celebrated_lottery.ViewHolder>{

    private List<CelebratedLottery> listCelebratedLottery;
    private TextView numTicketText;
    private TextView numTicket;

    public Adaptor_celebrated_lottery(List<CelebratedLottery> listCelebratedLottery){
        this.listCelebratedLottery = listCelebratedLottery;
    }

    public List<CelebratedLottery> getListCelebratedLottery(){ return listCelebratedLottery; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celebrated_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);
        numTicketText = (TextView) view.findViewById(R.id.numTicketCelebratedText);
        numTicket = (TextView) view.findViewById(R.id.numTicketCelebrated);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listCelebratedLottery.get(position).getName());
        holder.date.setText(listCelebratedLottery.get(position).getDate());
        controlTicketsArchived(holder, position);
        holder.photo.setImageResource(listCelebratedLottery.get(position).getPhoto());
    }

    public void controlTicketsArchived(ViewHolder holder, int position){
        if(listCelebratedLottery.get(position).getUser()==null){
            numTicketText.setVisibility(View.INVISIBLE);
            numTicket.setVisibility(View.INVISIBLE);
        }else{
            numTicketText.setVisibility(View.VISIBLE);
            numTicket.setVisibility(View.VISIBLE);
            holder.numTicketCelebrated.setText(listCelebratedLottery.get(position).getAmount()+"");
        }
    }

    @Override
    public int getItemCount() {
        return listCelebratedLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, numTicketCelebrated;
        private ImageView photo;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameCelebratedLottery);
            date=iterView.findViewById(R.id.dateCelebratedLottery);
            photo= iterView.findViewById(R.id.imgCelebratedLottery);
            numTicketCelebrated= iterView.findViewById((R.id.numTicketCelebrated));
        }
    }
}
