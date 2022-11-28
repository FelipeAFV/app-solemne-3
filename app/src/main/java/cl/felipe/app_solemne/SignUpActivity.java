package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cl.felipe.app_solemne.ws_cliente.MySingleton;

public class SignUpActivity extends AppCompatActivity {

    UserMantenedor userMantenedor = new UserMantenedor(this);

    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.contexto = this;
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View v) {
        String username = ((EditText) findViewById(R.id.txtSignUpUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.txtSignUpPassword)).getText().toString();

        //10.0.2.2:8000
        //54.84.14.179
        //String url ="http://10.0.2.2:8000/usuario/crear";
        String url ="http://54.84.14.179/usuario/crear";

        JSONObject body = new JSONObject();

        try {
            body.put("username", username);
            body.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("lLamando al server");
        JsonObjectRequest request = new JsonObjectRequest(url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            try {
                                //handle your response
                                Toast t = Toast.makeText(contexto, "Usuario ingresado con éxito", Toast.LENGTH_LONG);
                                t.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast t = Toast.makeText(contexto, "Error al registrarse", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(contexto).addToRequestQueue(request);
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
