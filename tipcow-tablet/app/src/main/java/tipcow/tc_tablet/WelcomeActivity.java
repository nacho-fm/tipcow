package tipcow.tc_tablet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        final EditText nameEditText = (EditText) findViewById(R.id.name_edit);
        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent textIntent = new Intent(WelcomeActivity.this, CheckTextActivity.class);
                    textIntent.putExtra("phoneNumber", WelcomeActivity.this.getIntent().getStringExtra("phoneNumber"));
                    textIntent.putExtra("firstName", String.valueOf(nameEditText.getText()));
                    startActivity(textIntent);
                    return true;
                }
                return false;
            }
        });
    }
}
