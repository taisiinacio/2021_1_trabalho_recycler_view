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
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> listaComment;
    private int layout;

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public View viewComment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewComment = itemView;
        }
    }
    public CommentAdapter(List<Comment> comment, int layout) {
        this.listaComment = comment;
        this.layout = layout;
        if (this.layout == 0) {
            this.layout =  R.layout.item_comment;
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if (this.listaComment.get(position) instanceof Comment) {
            Comment obj = (Comment)this.listaComment.get(position);
            TextView tv;
            tv = holder.viewComment.findViewById(R.id.tv_post_id);
            tv.setText(obj.getName());
            tv = holder.viewComment.findViewById(R.id.tv_album_title);
            tv.setText(""+obj.getPostId());
            tv = holder.viewComment.findViewById(R.id.tv_album_userId);
            tv.setText(""+obj.getId());
            tv = holder.viewComment.findViewById(R.id.tv_post_body);
            tv.setText(""+obj.getEmail());
            tv = holder.viewComment.findViewById(R.id.tv_comment_body);
            tv.setText(""+obj.getBody());


                CardView bt = holder.viewComment.findViewById(R.id.card_comment);
                bt.setTag(obj);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CardView btn = (CardView) v;
                        Comment Comment = (Comment) btn.getTag();
                        Intent intent = new Intent(holder.viewComment.getContext(), DetalheActivity.class);

                        // adicional para incluir dados para a proxima activity
                        intent.putExtra("obj", obj);
                        // lança intent para o SO chamar a activity
                        holder.viewComment.getContext().startActivity(intent);
                    }
                });

        }


    }



    @Override
    public int getItemCount() {
        return this.listaComment.size();
    }
}
