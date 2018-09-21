package cynthia.blocklotto.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class Adaptor_pending_lottery extends RecyclerView.Adapter<Adaptor_pending_lottery.ViewHolder>{

    private List<PendingLottery> listPendingLottery;

    public Adaptor_pending_lottery(List<PendingLottery> listPendingLottery){
        this.listPendingLottery = listPendingLottery;
    }

    public List<PendingLottery> getListPendingLottery(){ return listPendingLottery;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_lottery, parent, false);
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

                TextView text = (TextView) layout.findViewById(R.id.textToast);
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
        holder.name.setText(listPendingLottery.get(position).getName());
        holder.date.setText(listPendingLottery.get(position).getDate());
        holder.archivedButton.setText(listPendingLottery.get(position).getButton());
        holder.photo.setImageResource(listPendingLottery.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return listPendingLottery.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, date;
        private ImageView photo;
        private Button archivedButton;

        public ViewHolder(View iterView){
            super(iterView);
            name=iterView.findViewById(R.id.namePendingLottery);
            date=iterView.findViewById(R.id.datePendingLottery);
            photo= iterView.findViewById(R.id.imgPendingLottery);
            archivedButton= iterView.findViewById(R.id.archivedButton);
        }

        public Button getButton() { return archivedButton;}
    }
}
