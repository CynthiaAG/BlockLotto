package cynthia.blocklotto.adapter.wallet;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.wallet.Transaction;

public class Adapter_item_transaction extends BaseAdapter {

    private Context  context;
    private ArrayList<Transaction> items;

    public Adapter_item_transaction (Context context, ArrayList<Transaction> items) {
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

    public void addAll(ArrayList<Transaction> transactions) {
        for (int i = 0 ; i < transactions.size(); i++) {
            items.add(transactions.get(i));
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
        convertView = layoutInflater.inflate(R.layout.item_transaction_list, null);
        Transaction transaction = items.get(position);

        TextView concept = (TextView) convertView.findViewById(R.id.transaction_concept);
        concept.setText(transaction.getConcept());

        TextView date = (TextView) convertView.findViewById(R.id.dateTransaction);
        date.setText(transaction.getDate());

        TextView balanceCurrent = convertView.findViewById(R.id.balanceCurrent);
        balanceCurrent.setText(transaction.getBalanceCurrentBadge());

        controlColor(transaction, convertView);

        return convertView;
    }

    private void controlColor(Transaction transaction, View convertView){
        TextView amount = (TextView) convertView.findViewById(R.id.amountTransaction);

        if(transaction.getType().equals("income")){
            amount.setText(transaction.getAmountType());
        }else{
            amount.setTextColor(ContextCompat.getColor(context, R.color.colorTertiary));
            amount.setText(transaction.getAmountType());
        }
    }
}
