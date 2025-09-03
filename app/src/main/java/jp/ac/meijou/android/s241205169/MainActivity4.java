package jp.ac.meijou.android.s241205169;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205169.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {

    private ActivityMain4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ここから
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ここまで

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // MainActivity2から渡された文字列を取得して表示
        var editText = getIntent().getStringExtra("title");
        binding.textView4.setText(editText);

        // OKボタン
        binding.buttonOk.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");       // "ret"というキーで"OK"という文字列を返す
            setResult(RESULT_OK, intent);
            finish();       // 画面を強制的に閉じる
        });


        // キャンセルボタン
        binding.buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);     // キャンセルなのでIntentは不要。値無しでキャンセル
            finish();       // 画面を強制的に閉じる
        });

    }
}