package com.example.demopaypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import static org.apache.http.util.EncodingUtils.*;


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

    private static final String URL_STRING = "https://www.paypal.com/cgi-bin/webscr";

    private void postData(String amount, String recipient) throws IOException {
        WebView webView = new WebView(this);

        setContentView(webView);

        String postData = "cmd=_xclick&business="+recipient+"&lc=US&amount="+amount+".00&currency_code=USD&button_subtype=services";

        webView.postUrl(URL_STRING,getBytes(postData,"Base64"));

    }
}