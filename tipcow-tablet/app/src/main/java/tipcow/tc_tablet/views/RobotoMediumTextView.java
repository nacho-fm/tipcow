package tipcow.tc_tablet.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RobotoMediumTextView extends AppCompatTextView {
    public RobotoMediumTextView(Context context) {
        super(context);
        setFont();
    }
    public RobotoMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public RobotoMediumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}