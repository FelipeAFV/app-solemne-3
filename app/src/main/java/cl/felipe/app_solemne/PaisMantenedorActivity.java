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

import cl.felipe.app_solemne.ws_cliente.PaisMantenedor;

public class PaisMantenedorActivity extends ActivityConfigSensor {


    public PaisMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_mantenedor);
        this.listView = findViewById(R.id.paisList);
        this.mantenedor = new PaisMantenedor(this, this.listView);

    }


    public void create(View v) {

        String name = ((EditText) findViewById(R.id.txtPaisName)).getText().toString();
        String abb = ((EditText) findViewById(R.id.txtPaisAbrebiacion)).getText().toString();
        Pais a = new Pais(name, abb);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        mantenedor.findAll();
    }

    public void delete(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtPaisId)).getText().toString());
        mantenedor.delete(id);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtPaisId)).getText().toString());
        String name = ((EditText) findViewById(R.id.txtPaisName)).getText().toString();
        String abb = ((EditText) findViewById(R.id.txtPaisAbrebiacion)).getText().toString();
        Pais a = new Pais(id, name, abb);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }

}
