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
    private TextView accumulatedTextAux;
    private TextView accumulatedAux;
    private Button buy;

    public Adaptor_next_lottery(List<NextLottery> listNextLottery){
        this.listNextLottery = listNextLottery;
    }

    public List<NextLottery> getListNextLottery(){return listNextLottery;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_next_lottery, parent, false);
        final ViewHolder viewHolder= new ViewHolder(view);
        accumulatedTextAux = (TextView) view.findViewById(R.id.accumulatedNextText);
        accumulatedAux = (TextView) view.findViewById(R.id.accumulatedNext);
        buy= (Button) view.findViewById(R.id.buyButton);

        //Button buy = viewHolder.getButton();

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
      //  holder.buyButton.setText(listNextLottery.get(position).getButton());
        holder.photo.setImageResource(listNextLottery.get(position).getPhoto());
        controlAccumulated(holder, position);

    }

    private void controlAccumulated(ViewHolder holder, int position){
        if(listNextLottery.get(position).getAccumulated() == null){
            accumulatedAux.setVisibility(View.INVISIBLE);
            accumulatedTextAux.setVisibility(View.INVISIBLE);
        }else{
            accumulatedAux.setVisibility(View.VISIBLE);
            accumulatedTextAux.setVisibility(View.VISIBLE);
            holder.accumulated.setText(listNextLottery.get(position).getAccumulated());
        }
    }

    @Override
    public int getItemCount() {
        return listNextLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, price, accumulated;
        private ImageView photo;
       // private Button buyButton;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameNextLottery);
            date=iterView.findViewById(R.id.dateNextLottery);
            price=iterView.findViewById(R.id.priceNextLottery);
            photo = iterView.findViewById(R.id.imgNextLottery);
           // buyButton= iterView.findViewById(R.id.buyButton);
            accumulated=iterView.findViewById(R.id.accumulatedNext);
        }

      //  public Button getButton(){ return buyButton; }
    }
}
