package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UserMantenedor userMantenedor = new UserMantenedor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) {

        EditText usernameField = findViewById(R.id.txtUsername);
        EditText passwordField = findViewById(R.id.txtPassword);

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        User user = userMantenedor.findByUsername(username);

        if (user != null) {
            System.out.println(user.getUsername() + " " + user.getPassword());
            message("Usuario existe");
        } else {
            message("Usuario no existe");
        }

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