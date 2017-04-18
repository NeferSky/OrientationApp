package nefersky.orientationapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Surface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String mOrientation = "";
    private EditText mEditText;
    boolean mState = false;
    private static final String ORIENTATION_ALBUM = "Album Orientation";
    private static final String ORIENTATION_BOOK = "Book orientation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText =  (EditText)findViewById(R.id.editText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, "onTextChanged: " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mEditText.setText(ORIENTATION_ALBUM);
    }

    private String getScreenOrientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return "Portrait orientation";
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "Landscape orientation";
        else
            return "Unknown orientation";
    }

    private boolean isLandscapeMode(Activity activity) {
        int Width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int Height = activity.getWindowManager().getDefaultDisplay().getHeight();

        boolean isLandscape = Width > Height;

        if (isLandscape)
            mOrientation = "Landscape Mode";
        else
            mOrientation = "Portrait Mode";

        return isLandscape;
    }

    private String getRotateOrientation() {
        int Rotate = getWindowManager().getDefaultDisplay().getRotation();
        switch (Rotate) {
            case Surface.ROTATION_0:
                return "Normal position";
            case Surface.ROTATION_90:
                return "90 degrees clockwise";
            case Surface.ROTATION_180:
                return "Upside down";
            case Surface.ROTATION_270:
                return "90 degrees counterclock-wise";
            default:
                return "Unknown";
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickFirst(View view) {
        mEditText.setText(getScreenOrientation());
    }

    public void onClickSecond(View view) {
        isLandscapeMode(this);
        mEditText.setText(mOrientation);
    }

    public void onClickThird(View view) {
        mEditText.setText(getRotateOrientation());
    }

    public void onClickFourth(View view) {
        if (!mState) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mEditText.setText(ORIENTATION_ALBUM);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mEditText.setText(ORIENTATION_BOOK);
        }
        mState = !mState;
    }
}

