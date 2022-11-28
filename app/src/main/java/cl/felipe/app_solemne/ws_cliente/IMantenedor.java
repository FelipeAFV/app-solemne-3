package cl.felipe.app_solemne.ws_cliente;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

public abstract class IMantenedor<T>  {

    protected Context context;
    //protected String apiUrl = "http://10.0.2.2:8000";
    protected String apiUrl = "http://54.84.14.179";


    public IMantenedor(Context context) {
        this.context = context;
    }

    protected boolean activeInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
    }

    public abstract T save(T t);
    public abstract T find(int id);
    public abstract List<T> findAll();
    public abstract List<T> findAll(int id);
    public abstract void delete(String id);
    public abstract void delete(int id);
    public abstract T edit(T t);


}
