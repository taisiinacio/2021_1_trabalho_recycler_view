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
import br.edu.uniritter.mobile.nossaprimeiraappnoite.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> listaPosts;
    private int layout;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public View viewPosts;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewPosts = itemView;
        }
    }

    public PostAdapter(List<Post> posts, int layout) {
        this.listaPosts = posts;
        this.layout = layout;
        if (this.layout == 0) {
            this.layout = R.layout.item_posts;
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        if (this.listaPosts.get(position) instanceof Post) {
            Post obj = (Post)this.listaPosts.get(position);
            TextView tv;
            tv = holder.viewPosts.findViewById(R.id.tv_comment_name);
            tv.setText(obj.getTitle());
            tv = holder.viewPosts.findViewById(R.id.tv_comment_postsId);
            tv.setText(""+obj.getUserId());
            tv = holder.viewPosts.findViewById(R.id.tv_comment_id);
            tv.setText(""+obj.getId());
            tv = holder.viewPosts.findViewById(R.id.tv_comment_email);
            tv.setText(obj.getBody());


            CardView bt = holder.viewPosts.findViewById(R.id.card_post);
            bt.setTag(obj);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CardView btn = (CardView) v;
                    Album Album = (Album) btn.getTag();
                    Intent intent = new Intent(holder.viewPosts.getContext(), DetalheActivity.class);

                    // adicional para incluir dados para a proxima activity
                    intent.putExtra("obj", obj);
                    // lança intent para o SO chamar a activity
                    holder.viewPosts.getContext().startActivity(intent);
                }
            });

        }



    }


    @Override
    public int getItemCount() {
        return this.listaPosts.size();
    }
}
