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

public class Adaptor_archived_lottery extends RecyclerView.Adapter<Adaptor_archived_lottery.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date;
        ImageView foto;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.nameArchivedLottery);
            date=iterView.findViewById(R.id.dateArchivedLottery);
            foto= iterView.findViewById(R.id.imgArchivedSorteo);
        }
    }

    public List<ArchivedLottery> listArchivedLottery;

    public Adaptor_archived_lottery(List<ArchivedLottery> listArchivedLottery){
        this.listArchivedLottery = listArchivedLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_archived_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listArchivedLottery.get(position).getName());
        holder.date.setText(listArchivedLottery.get(position).getDate());
        holder.foto.setImageResource(listArchivedLottery.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listArchivedLottery.size();
    }
}
