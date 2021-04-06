package br.edu.uniritter.mobile.nossaprimeiraappnoite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import br.edu.uniritter.mobile.nossaprimeiraappnoite.adapter.PostAdapter;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Post;

public class PostsRecyclerActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    List<Post> posts =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //LAYOUT
        setContentView(R.layout.activity_recycler_posts);


   //DADOS DO INTENT

        Intent it = getIntent();
        String nome = it.getStringExtra("nome");
        TextView tv = (TextView) findViewById(R.id.textoSegunda);
        tv.setText(nome);


    //CHAMADA DA API
        String url = "https://jsonplaceholder.typicode.com/posts";

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

            // Converte o json para uma lista de objetos java do tipo post
            for (int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                Post obj = new Post(json.getInt("userId"),
                        json.getInt("id"),
                        json.getString("title"),
                        json.getString("body"));
                posts.add(obj);

            }

            RecyclerView rv = findViewById(R.id.rv_comment);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            // GridLayoutManager glm = new GridLayoutManager(this,3);
            // StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);


            PostAdapter postsAdapter = new PostAdapter(posts, R.layout.item_posts);

            rv.setAdapter(postsAdapter);






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