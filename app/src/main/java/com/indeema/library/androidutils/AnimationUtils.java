package com.indeema.library.androidutils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/**
 * Utility methods for working with view animations.
 */

@SuppressWarnings({"unused", "restriction", "all"})
public class AnimationUtils {

    public static final int ANIMATION_DURATION_SHORT = 200;
    public static final int ANIMATION_DURATION_MEDIUM = 400;
    public static final int ANIMATION_DURATION_LONG = 800;

    public static final int SHOW_HIDE_ANIM_DURATION = 200;


    public interface AnimationEndListener {
        void onAnimationStart();

        void onAnimationEnd();
    }

    public interface AnimationListener {
        /**
         * @return true to override parent. Else execute Parent method
         */
        boolean onAnimationStart(View view);

        boolean onAnimationEnd(View view);

        boolean onAnimationCancel(View view);
    }

    public static void crossFadeViews(View showView, View hideView) {
        crossFadeViews(showView, hideView, SHOW_HIDE_ANIM_DURATION);
    }

    public static void crossFadeViews(View showView, final View hideView, int duration) {
        fadeInView(showView, duration);
        fadeOutView(hideView, duration);
    }


    public static void fadeInView(View view) {
        fadeInView(view, SHOW_HIDE_ANIM_DURATION);
    }

    public static void fadeInView(View view, int duration) {
        fadeInView(view, 0f, duration);
    }

    public static void fadeInView(View view, float initialAlpha, int duration) {
        view.setAlpha(initialAlpha);
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view).alpha(1f).setDuration(duration).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                view.setDrawingCacheEnabled(true);
            }

            @Override
            public void onAnimationEnd(View view) {
                view.setDrawingCacheEnabled(false);
            }

            @Override
            public void onAnimationCancel(View view) {
                view.setDrawingCacheEnabled(false);
            }
        });
    }

    public static void fadeOutView(View view) {
        fadeOutView(view, ANIMATION_DURATION_SHORT);
    }

    public static void fadeOutView(View view, int duration) {
        fadeOutView(view, 1f, duration);
    }

    public static void fadeOutView(View view, float initialAlpha, int duration) {
        view.setAlpha(initialAlpha);
        ViewCompat.animate(view).alpha(0f).setDuration(duration).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                view.setDrawingCacheEnabled(true);
            }

            @Override
            public void onAnimationEnd(View view) {
                view.setVisibility(View.GONE);
                view.setDrawingCacheEnabled(false);
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        });
    }


    public static void changeHeightWithDelay(final View view, final int initialHeight, final int targetHeight, long startDelay, int duration) {
        ValueAnimator heightAnimation = ValueAnimator.ofObject(new IntEvaluator(), initialHeight, targetHeight);
        heightAnimation.setStartDelay(startDelay);
        heightAnimation.setDuration(duration);
        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.getLayoutParams().height = ((int) animator.getAnimatedValue());
                view.requestLayout();
            }
        });
        heightAnimation.start();
    }


    public static void setNewHeight(final View view, final int targetHeight, int duration) {
        final int initialHeight = view.getMeasuredHeight();
        setNewHeight(view, initialHeight, targetHeight, duration);
    }

    public static void setNewHeight(final View view, final int initialHeight, final int targetHeight, int duration) {
        setNewHeight(view, initialHeight, targetHeight, duration, null);
    }

    public static void setNewHeight(final View view, final int initialHeight, final int targetHeight, int duration, final AnimationEndListener listener) {
        if (listener != null) listener.onAnimationStart();
        view.getLayoutParams().height = initialHeight;
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    if (targetHeight == 0) view.setVisibility(View.GONE);
                    if (listener != null) listener.onAnimationEnd();
                } else {
                    view.getLayoutParams().height = (int) (((targetHeight - initialHeight) * interpolatedTime) + initialHeight);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }


    public static void setNewWidth(final View view, final int targetHeight, int duration) {
        final int initialWidth = view.getMeasuredWidth();
        setNewWidth(view, initialWidth, targetHeight, duration);
    }

    public static void setNewWidth(final View view, final int initialWidth, final int targetWidth, int duration) {
        setNewWidth(view, initialWidth, targetWidth, duration, null);
    }

    public static void setNewWidth(final View view, final int initialWidth, final int targetWidth, int duration, final AnimationEndListener listener) {
        if (listener != null) listener.onAnimationStart();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    if (targetWidth == 0) view.setVisibility(View.GONE);
                    if (listener != null) listener.onAnimationEnd();
                } else {
                    view.getLayoutParams().width = (int) (((targetWidth - initialWidth) * interpolatedTime) + initialWidth);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static void setNewSize(final View view, final int targetWidth, final int targetHeight, int duration) {
        final int initialWidth = view.getMeasuredWidth();
        final int initialHeight = view.getMeasuredHeight();
        setNewSize(view, initialWidth, initialHeight, targetWidth, targetHeight, duration, null);
    }

    public static void setNewSize(final View view, final int targetWidth, final int targetHeight, int duration, AnimationEndListener listener) {
        final int initialWidth = view.getMeasuredWidth();
        final int initialHeight = view.getMeasuredHeight();
        setNewSize(view, initialWidth, initialHeight, targetWidth, targetHeight, duration, listener);
    }

    public static void setNewSize(final View view, final int initialWidth, final int initialHeight,
                                  final int targetWidth, final int targetHeight, int duration) {

        setNewSize(view, initialWidth, initialHeight, targetWidth, targetHeight, duration, null);
    }

    public static void setNewSize(final View view, final int initialWidth, final int initialHeight,
                                  final int targetWidth, final int targetHeight, int duration, final AnimationEndListener listener) {

        if (listener != null) listener.onAnimationStart();
        view.getLayoutParams().width = initialWidth;
        view.getLayoutParams().height = initialHeight;
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    if (targetWidth == 0 || targetHeight == 0) view.setVisibility(View.GONE);
                    if (listener != null) listener.onAnimationEnd();
                } else {
                    view.getLayoutParams().width = (int) (((targetWidth - initialWidth) * interpolatedTime) + initialWidth);
                    view.getLayoutParams().height = (int) (((targetHeight - initialHeight) * interpolatedTime) + initialHeight);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }


    public static void setNewSizeAndMargin(final View view, final int initialWidth, final int initialHeight,
                                           final int targetWidth, final int targetHeight,
                                           final int[] initialMargin, final int[] targetMargin,
                                           int duration, final AnimationEndListener listener) {

        if (listener != null) listener.onAnimationStart();
        view.getLayoutParams().width = initialWidth;
        view.getLayoutParams().height = initialHeight;
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {

                    view.getLayoutParams().width = targetWidth;
                    view.getLayoutParams().height = targetHeight;
                    ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(targetMargin[0], targetMargin[1], targetMargin[2], targetMargin[3]);
                    view.requestLayout();

                    if (targetWidth == 0 || targetHeight == 0) view.setVisibility(View.GONE);
                    if (listener != null) listener.onAnimationEnd();

                } else {
                    int left = (int) (((targetMargin[0] - initialMargin[0]) * interpolatedTime) + initialMargin[0]);
                    int top = (int) (((targetMargin[1] - initialMargin[1]) * interpolatedTime) + initialMargin[1]);
                    int right = (int) (((targetMargin[2] - initialMargin[2]) * interpolatedTime) + initialMargin[2]);
                    int bottom = (int) (((targetMargin[3] - initialMargin[3]) * interpolatedTime) + initialMargin[3]);
                    ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(left, top, right, bottom);

                    view.getLayoutParams().width = (int) (((targetWidth - initialWidth) * interpolatedTime) + initialWidth);
                    view.getLayoutParams().height = (int) (((targetHeight - initialHeight) * interpolatedTime) + initialHeight);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static void setTextSize(final TextView view, final float targetTextSizePx, int duration) {
        final float initialTextSizePx = view.getTextSize();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX, ((targetTextSizePx - initialTextSizePx) * interpolatedTime) + initialTextSizePx);
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static void setMargin(final View view, final int[] initialMargin, final int[] targetMargin, int duration) {
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(targetMargin[0], targetMargin[1], targetMargin[2], targetMargin[3]);
                    view.requestLayout();
                } else {
                    int left = (int) (((targetMargin[0] - initialMargin[0]) * interpolatedTime) + initialMargin[0]);
                    int top = (int) (((targetMargin[1] - initialMargin[1]) * interpolatedTime) + initialMargin[1]);
                    int right = (int) (((targetMargin[2] - initialMargin[2]) * interpolatedTime) + initialMargin[2]);
                    int bottom = (int) (((targetMargin[3] - initialMargin[3]) * interpolatedTime) + initialMargin[3]);

                    ((RelativeLayout.LayoutParams) view.getLayoutParams()).setMargins(left, top, right, bottom);
                    view.requestLayout();
                }
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }


    public static void setBackgroundColor(View view, int colorFrom, int colorTo, int duration) {
        setBackgroundColor(view, colorFrom, colorTo, 0, duration);
    }

    public static void setBackgroundColor(final View view, int colorFrom, int colorTo, long startDelay, int duration) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setStartDelay(startDelay);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    public static void hideView(View view) {
        hideView(view, SHOW_HIDE_ANIM_DURATION);
    }

    public static void hideView(View view, long duration) {
        hideView(view, 0, duration);
    }

    public static void hideView(final View view, long startDelay, long duration) {
        if (view.getVisibility() == View.GONE) return;
        view.animate().cancel();
        view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setStartDelay(startDelay)
                .setDuration(duration)
                .setInterpolator(new FastOutLinearInInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    private boolean mCancelled;

                    @Override
                    public void onAnimationStart(Animator animation) {
                        mCancelled = false;
                        view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mCancelled = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!mCancelled) {
                            view.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public static void showViewWithDelay(View view) {
        showViewWithDelay(view, SHOW_HIDE_ANIM_DURATION);
    }

    public static void showViewWithDelay(View view, long startDelay) {
        showView(view, startDelay, SHOW_HIDE_ANIM_DURATION);
    }

    public static void showView(View view) {
        showView(view, 0, SHOW_HIDE_ANIM_DURATION);
    }


    public static void showView(View view, long duration) {
        showView(view, 0, duration, null);
    }

    public static void showView(View view, long duration, AnimationEndListener listener) {
        showView(view, 0, duration, listener);
    }

    public static void showView(View view, long startDelay, long duration) {
        showView(view, startDelay, duration, null);
    }

    public static void showView(final View view, long startDelay, long duration, final AnimationEndListener listener) {
        view.animate().cancel();
        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setStartDelay(startDelay)
                .setDuration(duration)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        if (listener != null) listener.onAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (listener != null) listener.onAnimationEnd();
                    }
                });
    }

    public static void scaleView(View view, float scaleX, float scaleY, long startDelay, long duration) {
        scaleView(view, scaleX, scaleY, startDelay, duration, null);
    }

    public static void scaleView(View view, float scaleX, float scaleY, long startDelay, long duration, final AnimationEndListener listener) {
        view.animate().cancel();
        view.animate()
                .scaleX(scaleX)
                .scaleY(scaleY)
                .setStartDelay(startDelay)
                .setDuration(duration)
                .setInterpolator(new FastOutLinearInInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (listener != null) listener.onAnimationEnd();
                    }
                });
    }

    public static void moveViewVertically(View view, int initialTranslationY, int targetTranslationY) {
        moveViewVertically(view, initialTranslationY, targetTranslationY, SHOW_HIDE_ANIM_DURATION, null);
    }

    public static void moveViewVertically(final View view, final int initialTranslationY, final int targetTranslationY, long duration, final AnimationEndListener listener) {
        if (listener != null) listener.onAnimationStart();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.setTranslationY(((targetTranslationY - initialTranslationY) * interpolatedTime) + initialTranslationY);

                if (interpolatedTime == 1 && listener != null) listener.onAnimationEnd();
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }


    public static void moveViewHorizontally(View view, int initialTranslationX, int targetTranslationX) {
        moveViewHorizontally(view, initialTranslationX, targetTranslationX, SHOW_HIDE_ANIM_DURATION, null);
    }

    public static void moveViewHorizontally(View view, int initialTranslationX, int targetTranslationX, long duration) {
        moveViewHorizontally(view, initialTranslationX, targetTranslationX, duration, null);
    }

    public static void moveViewHorizontally(final View view, final int initialTranslationX, final int targetTranslationX, long duration, final AnimationEndListener listener) {
        if (listener != null) listener.onAnimationStart();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.setTranslationX(((targetTranslationX - initialTranslationX) * interpolatedTime) + initialTranslationX);

                if (interpolatedTime == 1 && listener != null) listener.onAnimationEnd();
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

}
