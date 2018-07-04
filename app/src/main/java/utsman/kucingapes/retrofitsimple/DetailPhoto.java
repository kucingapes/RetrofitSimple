package utsman.kucingapes.retrofitsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photo);

        ImageView imageView = findViewById(R.id.img_detail);
        TextView tvTitle = findViewById(R.id.photo_title);
        TextView tvId = findViewById(R.id.photo_id);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        int id = getIntent().getIntExtra("id", 0);

        tvTitle.setText(title);
        tvId.setText(String.valueOf(id));
        Glide.with(getApplicationContext())
                .load(url)
                .into(imageView);
    }
}
