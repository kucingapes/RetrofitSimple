package utsman.kucingapes.retrofitsimple;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import utsman.kucingapes.margindecorationkece.MarginDecorationKece;

public class Favorit extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavAdapter myAdapter;
    private List<ModelFavorit> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        recyclerView = findViewById(R.id.recycler);
        myAdapter = new FavAdapter(list, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MarginDecorationKece(15, MarginDecorationKece.VERTICAL));
        recyclerView.setAdapter(myAdapter);
        //myAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString("key", null);
        Type type = new TypeToken<ArrayList<ModelFavorit>>() {}.getType();
        list = gson.fromJson(json, type);

        /*if (list == null) {
            list = new ArrayList<>();
        }*/
    }
}
