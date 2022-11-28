package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.felipe.app_solemne.ws_cliente.IBookMantenedor;
import cl.felipe.app_solemne.ws_cliente.UserMantenedor;
import cl.felipe.app_solemne.ws_cliente.WSBookMantenedor;

public class MainActivity extends AppCompatActivity  {


    private UserMantenedor userMantenedor = new UserMantenedor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) {

        //Intent signUpIntent = new Intent(this, MenuActivity.class);
        //startActivity(signUpIntent);
        EditText usernameField = findViewById(R.id.txtUsername);
        EditText passwordField = findViewById(R.id.txtPassword);
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        userMantenedor.find(username, password);



    }

    public void goToSignUp(View v) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);

    }

    private void message(String textMessage) {
        Toast t = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);
        t.show();
    }



}