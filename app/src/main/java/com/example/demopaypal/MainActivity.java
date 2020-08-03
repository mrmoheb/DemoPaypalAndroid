package com.example.demopaypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        final EditText recipient = findViewById(R.id.recipient);
        final EditText amount = findViewById(R.id.amount);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    postData(amount.getText().toString(),recipient.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void postData(String amount, String recipient) throws IOException {
        WebView webView = new WebView(this);

        setContentView(webView);

        String postData = "cmd=" + URLEncoder.encode("_xclick", "UTF-8") +
                "&business=" + URLEncoder.encode(recipient, "UTF-8")
                +"&lc=" + URLEncoder.encode("US", "UTF-8")
                +"&amount=" + URLEncoder.encode(amount, "UTF-8")
                +"&currency_code=" + URLEncoder.encode("USD", "UTF-8")
                +"&button_subtype=" + URLEncoder.encode("services", "UTF-8");
        webView.postUrl("https://www.paypal.com/cgi-bin/webscr",postData.getBytes());


    }
}