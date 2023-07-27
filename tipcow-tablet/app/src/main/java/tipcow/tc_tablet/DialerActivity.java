package tipcow.tc_tablet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialerActivity extends AppCompatActivity {
    private View mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialer);

        mContentView = findViewById(R.id.dialer_layout);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        RelativeLayout[] btns = {
                (RelativeLayout) findViewById(R.id.dialer_button_1),
                (RelativeLayout) findViewById(R.id.dialer_button_2),
                (RelativeLayout) findViewById(R.id.dialer_button_3),
                (RelativeLayout) findViewById(R.id.dialer_button_4),
                (RelativeLayout) findViewById(R.id.dialer_button_5),
                (RelativeLayout) findViewById(R.id.dialer_button_6),
                (RelativeLayout) findViewById(R.id.dialer_button_7),
                (RelativeLayout) findViewById(R.id.dialer_button_8),
                (RelativeLayout) findViewById(R.id.dialer_button_9),
                (RelativeLayout) findViewById(R.id.dialer_button_0),
                (RelativeLayout) findViewById(R.id.dialer_button_ok),
                (RelativeLayout) findViewById(R.id.dialer_button_back)};
        for (RelativeLayout btn: btns) {
            btn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        view.setAlpha(0.2f);
                        onDialerClick(view);
                    }
                    else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        view.setAlpha(1f);
                    }
                    return true;
                }
            });
        }
    }

    private void onDialerClick(View v) {
        Log.d("Dialer", String.valueOf(String.valueOf(v.getId())) + " pressed.");
        switch (v.getId()) {
            case R.id.dialer_button_1:
                addPhoneDigit(1);
                break;
            case R.id.dialer_button_2:
                addPhoneDigit(2);
                break;
            case R.id.dialer_button_3:
                addPhoneDigit(3);
                break;
            case R.id.dialer_button_4:
                addPhoneDigit(4);
                break;
            case R.id.dialer_button_5:
                addPhoneDigit(5);
                break;
            case R.id.dialer_button_6:
                addPhoneDigit(6);
                break;
            case R.id.dialer_button_7:
                addPhoneDigit(7);
                break;
            case R.id.dialer_button_8:
                addPhoneDigit(8);
                break;
            case R.id.dialer_button_9:
                addPhoneDigit(9);
                break;
            case R.id.dialer_button_0:
                addPhoneDigit(0);
                break;
            case R.id.dialer_button_back:
                removePhoneDigit();
                break;
            case R.id.dialer_button_ok:
                Log.d("Dialer", "OK pressed.");
                TextView phoneText = (TextView) findViewById(R.id.phone_text);
                if (isFullNumberEntered()) {
                    Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
                    welcomeIntent.putExtra("phoneNumber", String.valueOf(phoneText.getText()));
                    startActivity(welcomeIntent);
                }
                break;
            default:
                Log.d("Dialer", String.valueOf(v.getId()));
                break;
        }
    }

    private void addPhoneDigit(int pressedNum) {
        Log.d("Dialer", String.valueOf(pressedNum) + " pressed.");
        TextView phoneText = (TextView) findViewById(R.id.phone_text);
        if (phoneText.getText().length() == 17) {
            RelativeLayout okBtn = (RelativeLayout) findViewById(R.id.dialer_button_ok);
            okBtn.setVisibility(View.VISIBLE);
        }
        if (isFullNumberEntered()) {
            return;
        }
        else if (phoneText.getText().equals(getResources().getString(R.string.enter_phone))) {
            phoneText.setText("(" + String.valueOf(pressedNum));
        }
        else if (phoneText.getText().length() == 3) {
            phoneText.setText(phoneText.getText() + String.valueOf(pressedNum) + ") - ");
        }
        else if (phoneText.getText().length() == 10) {
            phoneText.setText(phoneText.getText() + String.valueOf(pressedNum) + " - ");
        }
        else {
            phoneText.setText(phoneText.getText() + String.valueOf(pressedNum));
        }
    }

    private void removePhoneDigit() {
        Log.d("Dialer", "Back pressed.");
        TextView phoneText = (TextView) findViewById(R.id.phone_text);
        if (isFullNumberEntered()) {
            RelativeLayout okBtn = (RelativeLayout) findViewById(R.id.dialer_button_ok);
            okBtn.setVisibility(View.INVISIBLE);
        }
        if (phoneText.getText().equals(getResources().getString(R.string.enter_phone))) {
            return;
        }
        else if (phoneText.getText().length() == 15) {
            phoneText.setText(String.valueOf(phoneText.getText()).substring(0, phoneText.getText().length() - 4));
        }
        else if (phoneText.getText().length() == 9) {
            phoneText.setText(String.valueOf(phoneText.getText()).substring(0, phoneText.getText().length() - 5));
        }
        else if (phoneText.getText().length() == 2) {
            phoneText.setText(getResources().getString(R.string.enter_phone));
        }
        else {
            phoneText.setText(String.valueOf(phoneText.getText()).substring(0, phoneText.getText().length() - 1));
        }
    }

    private boolean isFullNumberEntered(){
        TextView phoneText = (TextView) findViewById(R.id.phone_text);
        return phoneText.getText().length() == 18 && !phoneText.getText().equals(getResources().getString(R.string.enter_phone));
    }
}
