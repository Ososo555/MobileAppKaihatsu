package jp.ac.meijou.android.s241205169;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s241205169.databinding.ActivityMain5Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMainBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();   // HTTP通信するクライアント

    private final Moshi moshi = new Moshi.Builder().build();        // JSONをJavaのオブジェクトに変換する

    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);    // JSONをGistクラスに変換するAdapter

    private ActivityMain5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ここから
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ここまで setContentViewが２個にならないよう注意

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Request.Builderクラスでアクセス先を作成
        var request = new Request.Builder()
                .url("https://mura.github.io/meijou-android-sample/gist.json")
                .build();

        // enqueue()でサブスレッドで通信処理を行う
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            // 通信に失敗したら呼ばれる
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            // 通信に成功したら呼ばれる
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // レスポンスボディをGist型に変換
                var gist = gistJsonAdapter.fromJson(response.body().source());

                // 中身の取り出し
                Optional.ofNullable(gist)
                        .map(g -> g.files.get("OkHttp.txt"))
                        .ifPresent(gistFile -> {
                            // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                            runOnUiThread(() -> binding.textViewtest.setText(gistFile.content));
                        });
            }
        });
    }
}