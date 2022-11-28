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

import cl.felipe.app_solemne.ws_cliente.EditorialMantenedor;

public class EditorialMantenedorActivity extends ActivityConfigSensor {

    public EditorialMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial_mantenedor);
        this.listView = findViewById(R.id.editorialList);
        this.mantenedor = new EditorialMantenedor(this, this.listView);

    }


    public void create(View v) {

        String name = ((EditText) findViewById(R.id.txtEditorialName)).getText().toString();
        int abb = Integer.parseInt(((EditText) findViewById(R.id.txtEditorialPaisId)).getText().toString());
        Editorial a = new Editorial(name, abb);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        mantenedor.findAll();
    }

    public void delete(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtEditorialId)).getText().toString());
        mantenedor.delete(id);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtEditorialId)).getText().toString());
        String name = ((EditText) findViewById(R.id.txtEditorialName)).getText().toString();
        int abb = Integer.parseInt(((EditText) findViewById(R.id.txtEditorialPaisId)).getText().toString());
        Editorial a = new Editorial(id, name, abb);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }


}
