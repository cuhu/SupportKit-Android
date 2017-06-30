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
                /**
                 * addIssues - adds custom issues and tags. There are some defaults if left blank
                 *          defaults are :
                 *                 - feedback
                 *                 - payment
                 *                 - bug
                 *                 - legal
                 */
                SupportKitBuilder supportKitBuilder = new SupportKitBuilder();
                //supportKitBuilder.addIssue("I have some feedback", "feedback");
                //supportKitBuilder.addIssue("I found a bug", "bug");
                supportKitBuilder.setEmailTo("Example.email@gmail.com").show(getFragmentManager());
            }
        });

    }
}
