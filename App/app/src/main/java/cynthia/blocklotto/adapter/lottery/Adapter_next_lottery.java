package cynthia.blocklotto.adapter.lottery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cynthia.blocklotto.action.lottery.InfoLottery;
import cynthia.blocklotto.action.lottery.BuyLottery;
import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;

import static java.lang.Integer.parseInt;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adapter_next_lottery extends RecyclerView.Adapter<Adapter_next_lottery.ViewHolder>{

    private List<NextLottery> listNextLottery;
    private TextView accumulatedTextAux;
    private TextView accumulatedAux;
    private Button buy;
    private Button info;
    private View view;

    public Adapter_next_lottery(List<NextLottery> listNextLottery){
        this.listNextLottery = listNextLottery;
    }

    public List<NextLottery> getListNextLottery(){return listNextLottery;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_next_lottery, parent, false);
        final ViewHolder viewHolder= new ViewHolder(view);
        buy= (Button) view.findViewById(R.id.buyButton);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(c , BuyLottery.class);
                final int position = viewHolder.getAdapterPosition();
                sendElements(intent, position);
                c.startActivity(intent);
            }
        });

        info = (Button) view.findViewById(R.id.infoNextButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(c , InfoLottery.class);
                final int position = viewHolder.getAdapterPosition();
                sendElements(intent, position);
                c.startActivity(intent);
            }
        });
        return viewHolder;
    }

    private void sendElements(Intent intent, int position){
        intent.putExtra("type", "Next");
        intent.putExtra("id", listNextLottery.get(position).getId());
        intent.putExtra("date", listNextLottery.get(position).getDate());
        intent.putExtra("name", listNextLottery.get(position).getName());
        intent.putExtra("info", listNextLottery.get(position).getInformation());
        intent.putExtra("award", listNextLottery.get(position).getAward());
        intent.putExtra("price", (double) listNextLottery.get(position).getPrice());
        intent.putExtra("priceBadge", listNextLottery.get(position).getPriceBadge());
        intent.putExtra("totalParticipations", listNextLottery.get(position).getTotalParticipations());
        intent.putExtra("currentParticipations", listNextLottery.get(position).getCurrentParticipations());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listNextLottery.get(position).getName());
        holder.date.setText(listNextLottery.get(position).getDate());
        holder.price.setText(listNextLottery.get(position).getPriceBadge());
        holder.photo.setImageResource(listNextLottery.get(position).getPhoto());
        holder.award.setText(listNextLottery.get(position).getAward());
        holder.buy.setEnabled(true);
        holder.buy.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.colorButton)));
        if( ((listNextLottery.get(position).getCurrentParticipations()) == (listNextLottery.get(position).getTotalParticipations())) || (listNextLottery.get(position).getBlocked() == 1)){
            holder.buy.setEnabled(false);
            holder.buy.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.text_color_quinary)));
        }

    }
    @Override
    public int getItemCount() {
        return listNextLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, price, award;
        private ImageView photo;
        private Button buy;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameNextLottery);
            date=iterView.findViewById(R.id.dateNextLottery);
            price=iterView.findViewById(R.id.priceNextLottery);
            photo = iterView.findViewById(R.id.imgNextLottery);
            award=iterView.findViewById(R.id.awardNext);
            buy = iterView.findViewById(R.id.buyButton);
        }
    }
}
