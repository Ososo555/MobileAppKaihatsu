package jp.ac.meijou.android.s241205169;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import jp.ac.meijou.android.s241205169.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    private String currentInput = "";   // 入力中のテキストを保持する変数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ここから
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ここまで

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ACボタン
        binding.buttonAC.setOnClickListener(v -> {
            currentInput = ""; // 入力内容を空にする
            binding.result.setText("0"); // 表示も初期状態に戻す
        });

        // イコールボタン
        binding.buttonEqual.setOnClickListener(v -> {
            try {
                Expression expression = new ExpressionBuilder(currentInput).build();
                double result = expression.evaluate();
                binding.result.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
            } catch (Exception e) {
                binding.result.setText("Error");
                currentInput = "";
            }
        });

        // 足し算ボタン
        binding.buttonPlus.setOnClickListener(v -> {
            currentInput += "+";
            binding.result.setText(currentInput);
        });

        // 引き算ボタン
        binding.buttonSubtract.setOnClickListener(v -> {
            currentInput += "-";
            binding.result.setText(currentInput);
        });

        // 掛け算ボタン
        binding.buttonMultiply.setOnClickListener(v -> {
            currentInput += "*";
            binding.result.setText(currentInput);
        });

        // 割り算ボタン
        binding.buttonDivide.setOnClickListener(v -> {
            currentInput += "/";
            binding.result.setText(currentInput);
        });

        // 1ボタン
        binding.button1.setOnClickListener(v -> {
            currentInput += "1";
            binding.result.setText(currentInput);
        });

        // 2ボタン
        binding.button2.setOnClickListener(v -> {
            currentInput += "2";
            binding.result.setText(currentInput);
        });

        // 3ボタン
        binding.button3.setOnClickListener(v -> {
            currentInput += "3";
            binding.result.setText(currentInput);
        });

        // 4ボタン
        binding.button4.setOnClickListener(v -> {
            currentInput += "4";
            binding.result.setText(currentInput);
        });

        // 5ボタン
        binding.button5.setOnClickListener(v -> {
            currentInput += "5";
            binding.result.setText(currentInput);
        });

        // 6ボタン
        binding.button6.setOnClickListener(v -> {
            currentInput += "6";
            binding.result.setText(currentInput);
        });

        // 7ボタン
        binding.button7.setOnClickListener(v -> {
            currentInput += "7";
            binding.result.setText(currentInput);
        });

        // 8ボタン
        binding.button8.setOnClickListener(v -> {
            currentInput += "8";
            binding.result.setText(currentInput);
        });

        // 9ボタン
        binding.button9.setOnClickListener(v -> {
            currentInput += "9";
            binding.result.setText(currentInput);
        });

    }
}