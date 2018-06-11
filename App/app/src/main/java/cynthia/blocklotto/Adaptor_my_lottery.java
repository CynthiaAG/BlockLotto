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

public class Adaptor_my_lottery extends RecyclerView.Adapter<Adaptor_my_lottery.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date;
        ImageView foto;
        Button archived;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameMyLottery);
            date=iterView.findViewById(R.id.dateMyLottery);
            foto= iterView.findViewById(R.id.imgMyLottery);
            archived= iterView.findViewById(R.id.buyMyLottery);
        }
    }

    public List<MyLottery> listMyLottery;

    public Adaptor_my_lottery(List<MyLottery> listMyLottery){
        this.listMyLottery = listMyLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listMyLottery.get(position).getName());
        holder.date.setText(listMyLottery.get(position).getDate());
        holder.archived.setText(listMyLottery.get(position).getButton());
        holder.foto.setImageResource(listMyLottery.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listMyLottery.size();
    }
}
