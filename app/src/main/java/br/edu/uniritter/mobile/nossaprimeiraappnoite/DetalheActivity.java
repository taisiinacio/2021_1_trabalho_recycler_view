package br.edu.uniritter.mobile.nossaprimeiraappnoite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Album;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Comment;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Photo;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Post;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Todo;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Intent intent = getIntent();
        Parcelable prc = intent.getParcelableExtra("obj");

        if (prc instanceof Post) {
            setContentView(R.layout.activity_detalhe_post);
            Post post = intent.getParcelableExtra("obj");
            bind(post);
        } else if (prc instanceof Photo) {
            setContentView(R.layout.activity_detalhe_photo);
            Photo photo = intent.getParcelableExtra("obj");
            bind(photo);
        } else if (prc instanceof Album) {
            setContentView(R.layout.activity_detalhe_album);
            Album album = intent.getParcelableExtra("obj");
            bind(album);
        } else if (prc instanceof Comment) {
            setContentView(R.layout.activity_detalhe_comment);
            Comment comment = intent.getParcelableExtra("obj");
            bind(comment);
        }


    }

    private void bind(Post obj) {
        TextView tv = findViewById(R.id.tvuserIdPost);
        tv.setText(obj.getUserId() + "");
        tv = findViewById(R.id.tvIdPost);
        tv.setText(obj.getId() + "");
        tv = findViewById(R.id.tvTitlePost);
        tv.setText(obj.getTitle());
        tv =  findViewById(R.id.tvBodyPost);
        tv.setText(obj.getBody());

    }

    private void bind(Album obj) {
        TextView tv = findViewById(R.id.tvuserIdAlbum);
        tv.setText(obj.getUserId()+"");
        tv = findViewById(R.id.tvIdAlbum);
       tv.setText(obj.getId()+"");
        tv = findViewById(R.id.tvTitleAlbum);
        tv.setText(obj.getTitle());

    }


    private void bind(Comment obj) {
        TextView tv = findViewById(R.id.tvpostIdComment);
        tv.setText(obj.getPostId()+"");
        tv = findViewById(R.id.tvIdComment);
        tv.setText(obj.getId()+"");
        tv = findViewById(R.id.tvNameComment);
        tv.setText(obj.getName());
        tv = findViewById(R.id.tvEmailComment);
        tv.setText(obj.getEmail());
        tv = findViewById(R.id.tvbodyComment);
        tv.setText(obj.getBody());

    }


    private void bind(Photo obj) {
        TextView tv = findViewById(R.id.tvAlbumPhoto);
        tv.setText(obj.getAlbumId() + "");
        tv = findViewById(R.id.tvIdPhoto);
        tv.setText(obj.getId() + "");
        tv = findViewById(R.id.tvTitlePhoto);
        tv.setText(obj.getTitle());
        tv = findViewById(R.id.tvUrlPhoto);
        tv.setText(obj.getUrl());
        tv = findViewById(R.id.tvThumbnailUrl);
        tv.setText(obj.getThumbnailUrl());

    }


}