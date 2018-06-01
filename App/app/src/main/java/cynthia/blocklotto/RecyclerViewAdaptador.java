package cynthia.blocklotto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date;
        ImageView foto;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameSorteo);
            date=iterView.findViewById(R.id.dateSorteo);
            foto= iterView.findViewById(R.id.imgSorteo);
        }
    }

    public List<NextLottery> listNextLottery;

    public RecyclerViewAdaptador(List<NextLottery> listNextLottery){
        this.listNextLottery = listNextLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listNextLottery.get(position).getName());
        holder.date.setText(listNextLottery.get(position).getDate());
        holder.foto.setImageResource(listNextLottery.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listNextLottery.size();
    }
}
