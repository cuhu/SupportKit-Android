package uk.dreamr.rhdev.supportkittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.dreamr.rhdev.dreamrsupportkit.SupportKitBuilder;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupportKitBuilder.getInstance().setBaseUrl("exampleUrl");
                SupportKitBuilder.getInstance().show(getFragmentManager());
            }
        });

    }
}
