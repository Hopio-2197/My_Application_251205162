package jp.ac.meijou.android.s251205162;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import io.reactivex.rxjava3.annotations.NonNull;
import jp.ac.meijou.android.s251205162.databinding.ActivityMain5Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private ActivityMain5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // setContentView(R.layout.activity_main5);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // "https://placehold.jp/350x350.png"
        // getImage("https://placehold.jp/350x350.png");

        // Getボタンを押した場合にイメージを変更
        binding.buttonGet.setOnClickListener(view ->{
            // テキストテキストボックスのテキストをtextに入力
            var text = binding.editTextTextText.getText().toString();

            // カラーテキストボックスのテキストをcolorに入力
            var color = binding.editTextTextColor.getText().toString();

            // textパラメータをつけた安全なURLの作成
            var url = Uri.parse("https://placehold.jp/")
                    .buildUpon()
                    // 上のURLにtextを追加
                    .appendQueryParameter("text", color + "500x500.png")

                    .appendQueryParameter("text", text)

                    // urlを作成
                    .build()
                    .toString();

            // getImageに渡す
            getImage(url);

        });
    }

    private void getImage(String url){
        // リクエストを作成
        var request = new Request.Builder()
                .url(url)
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.image.setImageBitmap(bitmap));
            }
        });
    }
}