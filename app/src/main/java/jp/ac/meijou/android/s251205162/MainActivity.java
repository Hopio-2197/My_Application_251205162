package jp.ac.meijou.android.s251205162;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205162.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Changeボタンが押されたらtextViewにstringのtext2を設定する
        /*binding.button.setOnClickListener(view ->{
            binding.textView.setText(R.string.text2);
        });*/

        // Changeボタンが押されたらtextboxに入力された文字列をtextviewに設定する
        binding.changeButton.setOnClickListener(view ->{
            String text = binding.editTextText.getText().toString();
            binding.textView.setText(text);
        });

        // ボタンが押されずともtextboxが変更されたらtextviewをリアルタイムで変更する
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                binding.textView.setText(text);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
        });

        // データストア
        prefDataStore = PrefDataStore.getInstance(this);
        // prefDataStore.getString("name").ifPresent(name -> binding.textView.setText(name));

        // 入力によって表示する画像を変える
        prefDataStore.getString("name").ifPresent(name ->
        {if ("a".equals(name)) {
            binding.textView.setText("前回保存されたのはAの画像");
            binding.imageView2.setImageResource(R.drawable.antigravity);
        }else if ("n".equals(name)) {
            binding.textView.setText("前回保存されたのはNの画像");
            binding.imageView2.setImageResource(R.drawable.baseline_cable_24);
        }else {
            binding.textView.setText("前回保存されたのは知らない画像");
            binding.imageView2.setImageResource(R.drawable.ic_launcher_foreground);
        }});

        // saveボタンが押されたらtextboxを保存する
        binding.saveButton.setOnClickListener(view -> {
            // 入力がaかbを含んでいるかどうかによって画像が変わる場合の処理
            String text = binding.editTextText.getText().toString();
            if ("a".equals(text)) {
                binding.imageView2.setImageResource(R.drawable.antigravity);
                binding.textView.setText("現在表示しているのはAの画像");
            }else if ("n".equals(text)) {
                binding.imageView2.setImageResource(R.drawable.baseline_cable_24);
                binding.textView.setText("現在表示しているのはNの画像");
            }else {
                binding.imageView2.setImageResource((R.drawable.ic_launcher_foreground));
                binding.textView.setText("現在表示しているのは知らない画像です");
            }
            prefDataStore.setString("name",text);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        // textViewにstringのtext1を設定する(方法1)
        TextView textView = findViewById(R.id.text_view);
        textView.setText(R.string.text1);

        // textViewにstringのtext1を設定する(方法2)
        binding.textView.setText(R.string.text1);
         */
    }
}