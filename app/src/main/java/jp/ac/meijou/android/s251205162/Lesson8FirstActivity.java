package jp.ac.meijou.android.s251205162;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205162.databinding.ActivityLesson8FirstBinding;

public class Lesson8FirstActivity extends AppCompatActivity {

    private ActivityLesson8FirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson8FirstBinding.inflate(getLayoutInflater());
        // setContentView(R.layout.activity_lesson8_second);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 明示的intent
        binding.lesson8Button1.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });

        // 暗黙的intent
        binding.lesson8Button2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.ButtonSend.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("greeting","Hello");
            startActivity(intent);
        });
    }
}