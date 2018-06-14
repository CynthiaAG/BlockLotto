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
import android.widget.Toast;

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
            archived= iterView.findViewById(R.id.archived);
        }

        public Button getButton() { return archived;}
    }

    public List<MyLottery> listMyLottery;

    public Adaptor_my_lottery(List<MyLottery> listMyLottery){
        this.listMyLottery = listMyLottery;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_lottery, parent, false);
        ViewHolder viewHolder= new ViewHolder(view);

        Button button = viewHolder.getButton();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Archivar sorteo

                Context context = view.getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) view.findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Sorteo archivado");

                Toast toast = new Toast(view.getContext());
                toast.setView(layout);
                toast.show();
            }
        });

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
