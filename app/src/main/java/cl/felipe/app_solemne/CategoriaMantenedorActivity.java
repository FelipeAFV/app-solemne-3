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

import cl.felipe.app_solemne.ws_cliente.CategoriaMantenedor;

public class CategoriaMantenedorActivity extends ActivityConfigSensor {

    public CategoriaMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_mantenedor);
        this.listView = findViewById(R.id.categoriaList);
        this.mantenedor = new CategoriaMantenedor(this, this.listView);

    }


    public void create(View v) {

        String name = ((EditText) findViewById(R.id.txtCatName)).getText().toString();
        String detail = ((EditText) findViewById(R.id.txtCatDetail)).getText().toString();
        Categoria a = new Categoria(name, detail);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        mantenedor.findAll();
    }

    public void delete(View v) {
        String name = ((EditText) findViewById(R.id.txtCatName)).getText().toString();
        mantenedor.delete(name);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtCatId)).getText().toString());
        String name = ((EditText) findViewById(R.id.txtCatName)).getText().toString();
        String detail = ((EditText) findViewById(R.id.txtCatDetail)).getText().toString();
        Categoria a = new Categoria(id, name, detail);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }

}
