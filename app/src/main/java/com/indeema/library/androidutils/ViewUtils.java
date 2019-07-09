package com.indeema.library.androidutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;


/**
 * Utility methods for working with views.
 */

@SuppressWarnings("unused")
public class ViewUtils {

    public static void setZ(Context contexts, View... views) {
        setZ(contexts, 4, TypedValue.COMPLEX_UNIT_DIP, views);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setZ(Context contexts, float value, int unit, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (View view : views) {
                view.setZ(TypedValue.applyDimension(unit, value, contexts.getResources().getDisplayMetrics()));
            }
        }
    }

    public static void hideViews(View... views) {
        for (View view : views) {
            hideView(view);
        }
    }

    public static void hideView(View view) {
        view.setVisibility(View.GONE);
        view.setScaleX(0f);
        view.setScaleY(0f);
        view.setAlpha(0f);
    }

    public static void showViews(View... views) {
        for (View view : views) {
            showView(view);
        }
    }

    public static void showView(View view) {
        view.setScaleX(1f);
        view.setScaleY(1f);
        view.setAlpha(1f);
        view.setVisibility(View.VISIBLE);
    }

    public static void setWidth(View view, int width) {
        view.getLayoutParams().width = width;
    }

    public static void setHeight(View view, int height) {
        view.getLayoutParams().height = height;
    }

    /**
     * @return view color,
     * if view has not ColorDrawable - Color.WHITE
     */
    public static int getColor(View view) {
        int color = Color.WHITE;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            color = ((ColorDrawable) background).getColor();
        }

        return color;
    }

    public static void setBackground(Context context, View view, @DrawableRes int backgroundResId) {
        setBackground(view, ContextCompat.getDrawable(context, backgroundResId));
    }

    public static void setBackground(View view, Drawable background) {
        view.setBackground(background);
    }

    public static RippleDrawable createRipple(@ColorInt int color, @FloatRange(from = 0f, to = 1f) float alpha, boolean bounded) {
        color = ColorUtils.modifyAlpha(color, alpha);
        return new RippleDrawable(ColorStateList.valueOf(color), null, bounded ? new ColorDrawable(Color.WHITE) : null);
    }

    public static RippleDrawable createRipple(@NonNull Palette palette,
                                              @FloatRange(from = 0f, to = 1f) float darkAlpha,
                                              @FloatRange(from = 0f, to = 1f) float lightAlpha,
                                              @ColorInt int fallbackColor, boolean bounded) {
        int rippleColor = fallbackColor;
        if (palette.getVibrantSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getVibrantSwatch().getRgb(), darkAlpha);
        } else if (palette.getLightVibrantSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getLightVibrantSwatch().getRgb(), lightAlpha);
        } else if (palette.getDarkVibrantSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getDarkVibrantSwatch().getRgb(), darkAlpha);
        } else if (palette.getMutedSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getMutedSwatch().getRgb(), darkAlpha);
        } else if (palette.getLightMutedSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getLightMutedSwatch().getRgb(), lightAlpha);
        } else if (palette.getDarkMutedSwatch() != null) {
            rippleColor = ColorUtils.modifyAlpha(palette.getDarkMutedSwatch().getRgb(), darkAlpha);
        }
        return new RippleDrawable(ColorStateList.valueOf(rippleColor), null,
                bounded ? new ColorDrawable(Color.WHITE) : null);
    }

}
