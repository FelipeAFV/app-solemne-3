package cl.felipe.app_solemne;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import cl.felipe.app_solemne.ws_cliente.ReclamoMantenedor;
import cl.felipe.app_solemne.ws_cliente.RecomendacionMantenedor;

public class RecomendacionMantenedorActivity  extends  ActivityConfigSensor{

    public RecomendacionMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion_mantenedor);
        this.listView = findViewById(R.id.recomList);
        this.mantenedor = new RecomendacionMantenedor(this, this.listView);

    }


    public void create(View v) {

        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        int autorId = Integer.parseInt(((EditText) findViewById(R.id.txtRecomAutorId)).getText().toString());
        String comment = ((EditText) findViewById(R.id.txtRecomComment)).getText().toString();
        Recomendacion a = new Recomendacion(comment,  autorId, userId);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        //int userId = Integer.parseInt(((EditText) findViewById(R.id.txtReclamoUserId)).getText().toString());
        int autorId = Integer.parseInt(((EditText) findViewById(R.id.txtRecomAutorId)).getText().toString());
        mantenedor.findAll(autorId);
    }

    public void delete(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtRecomId)).getText().toString());
        mantenedor.delete(id);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtRecomId)).getText().toString());
        String comment = ((EditText) findViewById(R.id.txtRecomComment)).getText().toString();
        Recomendacion a = new Recomendacion(id, comment);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }

}
