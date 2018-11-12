package cynthia.blocklotto.fragment.notification;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.notification.Adapter_item_notification;
import cynthia.blocklotto.notification.Notification;

public class Fragment_notification extends Fragment {


    private ListView notificationList;
    private View view;
    private ArrayList<Notification> notifications;
    private Adapter_item_notification adapter;
    private LayoutInflater inflater;
    private View customToast;
    private TextView text;
    private SwipeRefreshLayout swipeRefresh;


    private void initialize(){
        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("Notificación leída");

        swipeRefresh = view.findViewById(R.id.swipNotification);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        notificationList = (ListView) view.findViewById(R.id.notificationList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_notification, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        initialize();
        controlSwipe();
        adapter = new Adapter_item_notification(getContext(), getNotifications());
        notificationList.setAdapter(adapter);

        notificationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(notifications.get(i).getType().equals("notRead")){
                    notifications.get(i).setType("read");
                    adapter.notifyDataSetChanged();

                    Toast toast = new Toast(getContext());
                    toast.setView(customToast);
                    toast.show();
                }
            }
        });
        return view;
    }

    private void controlSwipe(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                notifications.clear();

                notifications.add(new Notification(1,"Ha ganado el premio ExtraCoin", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(1,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(2,"Ha ganado el premio CryptoLucky", "25/03/2018", 20, "notRead"));
                notifications.add(new Notification(3,"Ha ganado el premio Extracoin", "25/02/2018", 5, "read"));
                notifications.add(new Notification(4,"Ha ganado el premio CryptoLucky", "25/11/2017", 35, "notRead"));
                notifications.add(new Notification(5,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(6,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "read"));
                notifications.add(new Notification(7, "Ha ganado el premio Lotto Boom", "29/11/2017", 10, "notRead"));
                notifications.add(new Notification(8, "Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(9,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(10,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(11,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(12,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
                notifications.add(new Notification(13, "Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));

                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private ArrayList<Notification> getNotifications(){
        notifications = new ArrayList<Notification>();

        notifications.add(new Notification(1,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(2,"Ha ganado el premio CryptoLucky", "25/03/2018", 20, "notRead"));
        notifications.add(new Notification(3,"Ha ganado el premio Extracoin", "25/02/2018", 5, "read"));
        notifications.add(new Notification(4,"Ha ganado el premio CryptoLucky", "25/11/2017", 35, "notRead"));
        notifications.add(new Notification(5,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(6,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "read"));
        notifications.add(new Notification(7, "Ha ganado el premio Lotto Boom", "29/11/2017", 10, "notRead"));
        notifications.add(new Notification(8, "Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(9,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(10,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(11,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(12,"Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));
        notifications.add(new Notification(13, "Ha ganado el premio CryptoLucky", "25/03/2018", 10, "notRead"));

        return notifications;
    }

}
