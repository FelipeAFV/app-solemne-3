package cl.felipe.app_solemne.ws_cliente;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cl.felipe.app_solemne.MenuActivity;
import cl.felipe.app_solemne.User;

public class UserMantenedor extends IMantenedor<User> {

    public UserMantenedor(Context context) {
        super(context);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User find(int id) {
        return null;
    }

    public User find(String username, String password) {
        System.out.println("Internet connection "+ activeInternetConnection());
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;

        }

        String url = apiUrl + "/usuario/ingresar";

        JSONObject body = new JSONObject();

        try {
            body.put("username", username);
            body.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject o) {
                        if (null != o) {
                            try {

                                Intent menuIntent = new Intent(context, MenuActivity.class);
                                menuIntent.putExtra("userId", String.valueOf(o.getInt("userId")));
                                System.out.println("getting user id");
                                System.out.println(o.getInt("userId"));
                                context.startActivity(menuIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast t = Toast.makeText(context, "Error al ingresar usuario", Toast.LENGTH_LONG);
                                t.show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast t = Toast.makeText(context, "Error al ingresar usuario", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User edit(User user) {
        return null;
    }
}
