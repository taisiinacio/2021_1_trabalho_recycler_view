package br.edu.uniritter.mobile.nossaprimeiraappnoite.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.nossaprimeiraappnoite.DetalheActivity;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.R;
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> listaAlbum;
    private int layout;

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        public View viewAlbum;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewAlbum = itemView;
        }
    }
    public AlbumAdapter(List<Album> album, int layout) {
        this.listaAlbum = album;
        this.layout = layout;
        if (this.layout == 0) {
            this.layout =  R.layout.item_album;
        }
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        if (this.listaAlbum.get(position) instanceof Album) {
            Album obj = (Album)this.listaAlbum.get(position);
            TextView tv;
            tv = holder.viewAlbum.findViewById(R.id.tv_album_id);
            tv.setText(""+obj.getId());
            tv = holder.viewAlbum.findViewById(R.id.tv_album_title);
            tv.setText(""+obj.getTitle());
            tv = holder.viewAlbum.findViewById(R.id.tv_album_userId);
            tv.setText(""+obj.getUserId());


                CardView bt = holder.viewAlbum.findViewById(R.id.card_album);
                bt.setTag(obj);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CardView btn = (CardView) v;
                        Album Album = (Album) btn.getTag();
                        Intent intent = new Intent(holder.viewAlbum.getContext(), DetalheActivity.class);

                        // adicional para incluir dados para a proxima activity
                        intent.putExtra("obj", obj);
                        // lança intent para o SO chamar a activity
                        holder.viewAlbum.getContext().startActivity(intent);
                    }
                });

        }


    }



    @Override
    public int getItemCount() {
        return this.listaAlbum.size();
    }
}
