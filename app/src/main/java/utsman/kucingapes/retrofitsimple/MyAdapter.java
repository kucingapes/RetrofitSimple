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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolderView> {
    private List<Model> modelList;
    private Context context;

    public MyAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item, viewGroup, false);
        context = viewGroup.getContext();

        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolderView myHolderView, @SuppressLint("RecyclerView") final int i) {
        myHolderView.judul.setText(modelList.get(i).getTitle());
        Glide.with(context)
                .load(modelList.get(i).getThumbnailUrl())
                .into(myHolderView.imageView);

        myHolderView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPhoto.class);
                intent.putExtra("url", modelList.get(i).getUrl());
                intent.putExtra("title", modelList.get(i).getTitle());
                intent.putExtra("id", modelList.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
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
