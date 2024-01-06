package au.com.darkside.x11server;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;
import au.com.darkside.xserver.ScreenView;

public class XServerScreen extends View {
  private static final String LOG_TAG = "Berara";
  private ScreenView _xScreenView;
  private ScaleGestureDetector mScaleDetector;
  private XServerScaleGesture mScaleListener;

  public XServerScreen(Context context, ScreenView _xScreenView) {
    super(context);
    this._xScreenView = _xScreenView;
    init(context);
  }

  public XServerScreen(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public XServerScreen(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mScaleListener = new XServerScaleGesture(this._xScreenView);
    mScaleDetector = new ScaleGestureDetector(context, mScaleListener);

    setOnTouchListener(new View.OnTouchListener() {
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    Log.d(LOG_TAG, "Pointer count: " + event.getPointerCount());
    // Toast.makeText(getContext(), "Touch event detected", Toast.LENGTH_SHORT).show();
    Toast.makeText(getContext(), "Pointer count: " + event.getPointerCount(), Toast.LENGTH_SHORT)
        .show();

    if (event.getPointerCount() == 3) {
      Toast.makeText(getContext(), "onTouch: Three finger touch detected", Toast.LENGTH_SHORT)
          .show();
      // toggleNavigationBar();
      return false;
    }

    mScaleDetector.onTouchEvent(event);
    if (mScaleListener.scaleInProgress == true) {
      Toast.makeText(getContext(), "mScaleListener.scaleInProgress is true", Toast.LENGTH_SHORT)
          .show();
      // Log.d(LOG_TAG, "mScaleListener.scaleInProgress is true");
      return false;
    }

    return true;
  }});

    // super.addView(_xScreenView);
  }

  
}