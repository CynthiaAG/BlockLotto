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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.adapter.notification.Adapter_item_notification;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;
import cynthia.blocklotto.notification.Notification;

public class Fragment_notification extends Fragment implements ConectionResponse {


    private ListView notificationList;
    private View view;
    private ArrayList<Notification> notifications;
    private Adapter_item_notification adapter;
    private LayoutInflater inflater;
    private View customToast;
    private TextView text;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private TextView notFound;

    private boolean read;
    private boolean notificationsB;

    private void initialize(){
        read = false;
        notificationsB = false;
        notFound = view.findViewById(R.id.notFound_notifications);
        progressBar = view.findViewById(R.id.progress_notifications);
        progressBar.setVisibility(View.VISIBLE);

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
        notifications = new ArrayList<Notification>();
        getNotifications();
        adapter = new Adapter_item_notification(getContext(), notifications);
        notificationList.setAdapter(adapter);

        notificationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(notifications.get(i).getReaded() == 0){
                    setReaded(notifications.get(i).getId());

                }
            }
        });
        return view;
    }

    private void setReaded(int id){
        read = true;
        Conection con = new Conection();
        con.conectionResponse=this;
        con.setNotificationsRead(id, getContext());

    }
    private void getNotifications(){
        notificationsB = true;
        Conection con = new Conection();
        con.conectionResponse=this;
        con.getNotifications(getContext());
    }

    private void controlSwipe(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotifications();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        if(output==null){
            showNotInternet();
        } else if (notificationsB){
            notificationsB = false;
            notifications.clear();
            ArrayList<Notification> aux = resultFromJson.getNotificationsResult(output);
            progressBar.setVisibility(View.INVISIBLE);
            if ((!aux.isEmpty()) && (aux != null)) {
                for (Notification next : aux) {
                    notifications.add(next);
                }
                adapter.notifyDataSetChanged();
                notFound.setVisibility(View.INVISIBLE);
            } else {
                notFound.setText("No tienes ninguna notificación");
                notFound.setVisibility(View.VISIBLE);
            }
        }else if(read){
            read = false;
            String message = resultFromJson.setNotificationsReadResult(output);
            if(message != null && (!message.equals("")) && (!message.isEmpty())){
                Toast toast = new Toast(getContext());
                toast.setView(customToast);
                toast.show();
                getNotifications();
            }
        }
    }

    private void showNotInternet(){
        notifications.clear();
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
        notFound.setText("No se pueden mostrar sus notificaciones. Revise su conexión a internet.");
        notFound.setVisibility(View.VISIBLE);
    }

}
