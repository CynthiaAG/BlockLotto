package cynthia.blocklotto;

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

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adaptor_next_lottery extends RecyclerView.Adapter<Adaptor_next_lottery.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, price;
        ImageView foto;
        Button buy;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameLottery);
            date=iterView.findViewById(R.id.dateLottery);
            price=iterView.findViewById(R.id.priceLottery);
            foto= iterView.findViewById(R.id.imgSorteo);
            buy= iterView.findViewById(R.id.buy);
        }

        public Button getButton(){ return buy; }
    }

    public List<NextLottery> listNextLottery;

    public Adaptor_next_lottery(List<NextLottery> listNextLottery){
        this.listNextLottery = listNextLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_next_lottery, parent, false);
        final ViewHolder viewHolder= new ViewHolder(view);

        Button buy = viewHolder.getButton();

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                c.startActivity(new Intent(c , BuyLottery.class));
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listNextLottery.get(position).getName());
        holder.date.setText(listNextLottery.get(position).getDate());
        holder.price.setText(listNextLottery.get(position).getPrice());
        holder.buy.setText(listNextLottery.get(position).getButton());
        holder.foto.setImageResource(listNextLottery.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listNextLottery.size();
    }
}
