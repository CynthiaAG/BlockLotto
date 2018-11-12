package cynthia.blocklotto.adapter.notification;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.notification.Notification;
import cynthia.blocklotto.wallet.Transaction;

import static cynthia.blocklotto.R.color.accentLight;

public class Adapter_item_notification extends BaseAdapter {

    private Context context;
    private ArrayList<Notification> items;

    public Adapter_item_notification (Context context, ArrayList<Notification> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Notification> notifications) {
        for (int i = 0 ; i < notifications.size(); i++) {
            items.add(notifications.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        convertView = layoutInflater.inflate(R.layout.item_notification, null);
        Notification notification = items.get(position);

        TextView concept = (TextView) convertView.findViewById(R.id.notification_concept);
        concept.setText(notification.getConcept());

        TextView date = (TextView) convertView.findViewById(R.id.dateNotification);
        date.setText(notification.getDate());

        TextView amount = convertView.findViewById(R.id.amountReceived);
        amount.setText(notification.getAmountBadge());

        ImageView icon = convertView.findViewById(R.id.iconNotification);
        icon.setImageResource(notification.getIcon());

        setBackgroundColor(notification, convertView);
        return convertView;
    }

    private void setBackgroundColor(Notification notification, View convertView){
        if(notification.getType().equals("notRead")){
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.accentLight2));
        }
    }
}
