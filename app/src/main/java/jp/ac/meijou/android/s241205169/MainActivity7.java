package jp.ac.meijou.android.s241205169;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s241205169.databinding.ActivityMain6Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMain7Binding;
import jp.ac.meijou.android.s241205169.databinding.ActivityMainBinding;

public class MainActivity7 extends AppCompatActivity {

    private ActivityMain7Binding binding;

    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ここから
        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ここまで setContentViewが２個にならないよう注意

        // getSystemService()でインスタンスConnectivityManagerを取得
        connectivityManager = getSystemService(ConnectivityManager.class);

        // 一番最初に使われるネットワーク情報の取得
        var currentNetwork = connectivityManager.getActiveNetwork();

        // ネットワーク情報を渡して表示を更新
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);

        /**
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         */
    }

    /**
     * アクティビティが前面に来たときに最新の情報を取得して表示
     * トランスポート（ネットワークの種類）の取得
     * @param network
     */
    private void updateTransport(Network network) {
        // ネットワークにどんな機能があるのか情報を取得
        var caps = connectivityManager.getNetworkCapabilities(network);

        if (caps != null) {
            // caps.hasTransport() でモバイル通信かWiFiかを判断してわかりやすいように文字列に直す
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            } else {
                transport = "その他";
            }

            binding.textViewTransport.setText(transport);
        }
    }

    /**
     * IPアドレスを取得して表示
     * @param network
     */
    private void updateIpAddress(Network network) {
        // ネットワークのリンクプロパティ（IPアドレスやDNSなど）を取得
        var linkProperties = connectivityManager.getLinkProperties(network);

        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));     // IPアドレスは複数ある場合があるので、改行で結合

            binding.textViewIPAddress.setText(addresses);
        }
    }

}