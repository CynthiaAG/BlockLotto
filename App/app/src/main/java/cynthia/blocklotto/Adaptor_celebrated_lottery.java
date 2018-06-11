package cynthia.blocklotto;

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

public class Adaptor_celebrated_lottery extends RecyclerView.Adapter<Adaptor_celebrated_lottery.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date, price;
        ImageView foto;
        Button buy;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameCelebratedLottery);
            date=iterView.findViewById(R.id.dateCelebratedLottery);
            foto= iterView.findViewById(R.id.imgCelebratedSorteo);;
        }
    }

    public List<CelebratedLottery> listCelebratedLottery;

    public Adaptor_celebrated_lottery(List<CelebratedLottery> listCelebratedLottery){
        this.listCelebratedLottery = listCelebratedLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celebrated_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listCelebratedLottery.get(position).getName());
        holder.date.setText(listCelebratedLottery.get(position).getDate());
        holder.foto.setImageResource(listCelebratedLottery.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listCelebratedLottery.size();
    }
}
