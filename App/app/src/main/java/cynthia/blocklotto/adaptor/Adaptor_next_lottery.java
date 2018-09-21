package cynthia.blocklotto.adaptor;

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

import cynthia.blocklotto.action.BuyLottery;
import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adaptor_next_lottery extends RecyclerView.Adapter<Adaptor_next_lottery.ViewHolder>{

    private List<NextLottery> listNextLottery;

    public Adaptor_next_lottery(List<NextLottery> listNextLottery){
        this.listNextLottery = listNextLottery;
    }

    public List<NextLottery> getListNextLottery(){return listNextLottery;}

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
        holder.buyButton.setText(listNextLottery.get(position).getButton());
        holder.foto.setImageResource(listNextLottery.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return listNextLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, price;
        private ImageView foto;
        private Button buyButton;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameNextLottery);
            date=iterView.findViewById(R.id.dateNextLottery);
            price=iterView.findViewById(R.id.priceNextLottery);
            foto= iterView.findViewById(R.id.imgNextLottery);
            buyButton= iterView.findViewById(R.id.buyButton);
        }

        public Button getButton(){ return buyButton; }
    }
}
