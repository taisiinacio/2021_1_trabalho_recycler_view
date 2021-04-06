package br.edu.uniritter.mobile.nossaprimeiraappnoite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    }

    public void abreSegundaActivity(View view) {
        Button bt = (Button) view;
        String nome = bt.getText().toString();
        Intent intent;
        if (nome.equals("Posts")){
            intent= new Intent(this, PostsRecyclerActivity.class);
        }
         else if (nome.equals("Albums")) {
            intent = new Intent(this, AlbumsRecyclerActivity.class);
        }
         else if (nome.equals("Comments")){
                intent = new Intent(this, CommentsRecyclerActivity.class);
            }
        else if (nome.equals("Photos")){
            intent = new Intent(this, PhotosActivity.class);
        }
        else {
            intent = new Intent(this, AlbumsActivity.class);
        }


        // Envia nome do resources
        intent.putExtra("nome", nome);

        // lança intent para o SO chamar a activity
        startActivity(intent);

    }
}