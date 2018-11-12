package cynthia.blocklotto.conection;

import android.content.Intent;
import android.os.IBinder;
import android.app.Service;

public class ServiceConection extends Service {

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("El servicio a Comenzado");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("El servicio a Terminado");
    }


}