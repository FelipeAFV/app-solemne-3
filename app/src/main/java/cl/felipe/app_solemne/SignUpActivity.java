package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    UserMantenedor userMantenedor = new UserMantenedor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View v) {
        try {
            String username = ((EditText) findViewById(R.id.txtSignUpUsername)).getText().toString();
            String password = ((EditText) findViewById(R.id.txtSignUpPassword)).getText().toString();
            String firstname = ((EditText) findViewById(R.id.txtSignUpFirstname)).getText().toString();
            String lastname = ((EditText) findViewById(R.id.txtSignUpLastname)).getText().toString();

            User user = new User(username, password, firstname, lastname);

            userMantenedor.saveUser(user);
            message("Usuario registrado");
        } catch (Exception e) {
            message("Error al registrarse");
        }

    }

    public void back(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void message(String textMessage) {
        Toast t = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);
        t.show();
    }
}
