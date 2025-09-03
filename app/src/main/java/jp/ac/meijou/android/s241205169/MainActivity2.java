package jp.ac.meijou.android.s241205169;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205169.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;


    /**
     * Lesson8: ほかのActivityへの画面遷移（遷移元）
     * onCreateの外に定義
     */
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))        // "ret"というキーで文字列を取得
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textViewResult.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.textViewResult.setText("Result: Canceled");
                    }
                    default -> {
                        binding.textViewResult.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ここから
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ここまで

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 明示的Intent
        binding.buttonMeiziIntent.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        // 暗黙的Intent
        binding.buttonAnziIntent.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp/"));
            startActivity(intent);
        });

        // sendボタン
        binding.buttonSend.setOnClickListener(view -> {
            // EditTextの文字列を取得
            var text = binding.editTextText3.getText().toString();
            var intent = new Intent(this, MainActivity4.class);     // MainActivity4を起動する明示的Intentを作成
            intent.putExtra("title", text);     // MainActivity4に"title"という名前のtextを渡す
            startActivity(intent);      // MainActivity4を起動
        });

        // startボタン
        binding.buttonStart.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity4.class);
            getActivityResult.launch(intent);       // MainActivity4を起動して結果を受け取る
        });

    }
}