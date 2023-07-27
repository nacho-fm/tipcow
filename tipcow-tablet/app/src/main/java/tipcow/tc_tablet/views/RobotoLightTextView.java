package tipcow.tc_tablet.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RobotoLightTextView extends AppCompatTextView {
    public RobotoLightTextView(Context context) {
        super(context);
        setFont();
    }
    public RobotoLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public RobotoLightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}