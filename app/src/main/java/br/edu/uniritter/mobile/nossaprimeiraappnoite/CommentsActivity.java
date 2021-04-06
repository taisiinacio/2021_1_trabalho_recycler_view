package br.edu.uniritter.mobile.nossaprimeiraappnoite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Comment;

public class CommentsActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    List<Comment> comments =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //LAYOUT
        setContentView(R.layout.activity_lista);


   //DADOS DO INTENT

        Intent it = getIntent();
        String nome = it.getStringExtra("nome");

        TextView tv = (TextView) findViewById(R.id.textoSegunda);
        tv.setText(nome);


    //CHAMADA DA API
        String url = "https://jsonplaceholder.typicode.com/comments";

        RequestQueue queue = Volley.newRequestQueue(this);
        // Request de JsonArray da URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);


        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);


    }
    // RESPOSTA DA API
    @Override
    public void onResponse(JSONArray response) {
        try {

            // Converte o json para uma lista de objetos java do tipo album
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                Comment obj = new Comment(json.getInt("postId"),
                        json.getInt("id"),
                        json.getString("name"),
                        json.getString("email"),
                        json.getString("body"));
                comments.add(obj);

            }

            //Percorre a lista de albums e cria um botão para cada item
            Toast.makeText(this,"qtd:"+ comments.size(),Toast.LENGTH_LONG).show();
            LinearLayout ll = findViewById(R.id.layoutVerticalItens);
            for(Comment obj1 : comments) {
                Button bt = new Button(this);
                bt.setText(obj1.getName());
                bt.setTag(obj1);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v;
                        Comment album = (Comment) btn.getTag();
                        Intent intent = new Intent(getApplicationContext(), DetalheActivity.class);

                        // adicional para incluir dados para a proxima activity
                        intent.putExtra("obj", album);
                        // lança intent para o SO chamar a activity
                        startActivity(intent);

                    }
                });
                ll.addView(bt);

            }


        } catch (JSONException e) {
            Log.e("erro",e.getMessage());
            e.printStackTrace();
        }
    }

    //ERRO DA API
    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        Toast.makeText(this.getApplicationContext(),"deu erro: "+msg,Toast.LENGTH_LONG).show();
    }



}