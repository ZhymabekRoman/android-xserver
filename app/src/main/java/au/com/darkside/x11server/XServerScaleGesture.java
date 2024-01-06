package au.com.darkside.x11server;

import au.com.darkside.xserver.ScreenView;
import android.view.ScaleGestureDetector;
import android.os.Handler;

public class XServerScaleGesture implements ScaleGestureDetector.OnScaleGestureListener {
    public Boolean scaleInProgress = false;
    private ScreenView _screenView;

    public XServerScaleGesture(ScreenView screenView) {
        this._screenView = screenView;
    }

    public Boolean getScaleInProgress() {
        return scaleInProgress;
    }

    public void setScaleInProgress(Boolean scaleInProgress) {
        this.scaleInProgress = scaleInProgress;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        this.scale(scaleFactor);
        return true;
    }

    public void scale(float scaleFactor) {
        scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
        this._screenView.setScaleX(scaleFactor);
        this._screenView.setScaleY(scaleFactor);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        setScaleInProgress(true);
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setScaleInProgress(false);
            }
        }, 1500);
    }

}