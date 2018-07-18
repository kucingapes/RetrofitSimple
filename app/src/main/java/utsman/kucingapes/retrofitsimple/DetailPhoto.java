package utsman.kucingapes.retrofitsimple;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import utsman.kucingapes.margindecorationkece.MarginDecorationKece;

public class DetailPhoto extends AppCompatActivity {

    private List<ModelFavorit> list = new ArrayList<>();
    private FavAdapter myAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photo);

        ImageView imageView = findViewById(R.id.img_detail);
        TextView tvTitle = findViewById(R.id.photo_title);
        TextView tvId = findViewById(R.id.photo_id);

        myAdapter = new FavAdapter(list, this);



        loadData();
        /*recyclerView = findViewById(R.id.rec_tes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MarginDecorationKece(15, MarginDecorationKece.VERTICAL));
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();*/
        //loadData();

        final Button save = findViewById(R.id.save);

        final String title = getIntent().getStringExtra("title");
        final String url = getIntent().getStringExtra("url");
        final int id = getIntent().getIntExtra("id", 0);

        tvTitle.setText(title);
        tvId.setText(String.valueOf(id));
        Glide.with(getApplicationContext())
                .load(url)
                .into(imageView);
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(title, url, id);
            }
        });
    }

    /*private void loadData() {

    }*/

    private void saveData(String title, String url, int id) {
        ModelFavorit favorit = new ModelFavorit(id, id, title, url, url);
        list.add(favorit);
        myAdapter.notifyDataSetChanged();
    }

    private void saveArrayList(List<ModelFavorit> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        //loadData(list, "key");
        //list = getArrayList("key");
        saveArrayList(list, "key");
        super.onBackPressed();
    }


    private void loadData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString("key", null);
        Type type = new TypeToken<ArrayList<ModelFavorit>>() {}.getType();
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new ArrayList<>();
        }
    }
}
