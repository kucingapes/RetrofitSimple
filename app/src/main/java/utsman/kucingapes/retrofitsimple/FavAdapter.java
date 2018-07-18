package utsman.kucingapes.retrofitsimple;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyHolderView> {
    private List<ModelFavorit> modelFavorits;
    private Context context;

    public FavAdapter(List<ModelFavorit> modelFavorits, Context context) {
        this.modelFavorits = modelFavorits;
        this.context = context;
    }

    @NonNull
    @Override
    public FavAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item, viewGroup, false);
        context = viewGroup.getContext();

        return new FavAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavAdapter.MyHolderView myHolderView, @SuppressLint("RecyclerView") final int i) {
        myHolderView.judul.setText(modelFavorits.get(i).getTitle());
        Glide.with(context)
                .load(modelFavorits.get(i).getThumbnailUrl())
                .into(myHolderView.imageView);

        myHolderView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPhoto.class);
                intent.putExtra("url", modelFavorits.get(i).getUrl());
                intent.putExtra("title", modelFavorits.get(i).getTitle());
                intent.putExtra("id", modelFavorits.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelFavorits.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        public TextView judul;
        public ImageView imageView;
        public CardView cardView;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            imageView = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.card_item);
        }
    }
}
