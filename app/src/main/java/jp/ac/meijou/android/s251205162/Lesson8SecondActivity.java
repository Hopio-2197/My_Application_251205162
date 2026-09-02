package jp.ac.meijou.android.s251205162;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205162.databinding.ActivityLesson8SecondBinding;

public class Lesson8SecondActivity extends AppCompatActivity {

    private ActivityLesson8SecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLesson8SecondBinding.inflate(getLayoutInflater());
        // setContentView(R.layout.activity_lesson8_second);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.lesson8Button1.setOnClickListener(view -> {
            var intent = new Intent(this, Lesson8SecondActivity.class);
            startActivity(intent);
        });

        binding.lesson8Button2.setOnClickListener(view -> {
            var intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            startActivity(intent);
        });
    }
}