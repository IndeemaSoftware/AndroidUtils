package com.indeema.library.androidutils;

/**
 * Utility methods for math operations.
 */

public class MathUtils {

    public static final int ROUND_WHOLE = 400;
    public static final int ROUND_HALF = 401;
    public static final int ROUND_TENTH = 402;
    public static final int ROUND_QUARTER = 403;
    public static final int ROUND_EIGHTH = 404;

    public static float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }

    public static float roundByType(float number, int type) {
        float result = 0;
        int base = (int) number;
        float delta = number - base;

        switch (type) {
            case ROUND_WHOLE:
                result = Math.round(number);
                break;
            case ROUND_HALF:
                if ((delta >= 0) && (delta < 0.25f)) {
                    delta = 0.0f;
                } else if ((delta >= 0.25f) && (delta < 0.75)) {
                    delta = 0.5f;
                } else {
                    delta = 1.0f;
                }
                result = base + delta;
                break;
            case ROUND_TENTH:
                number *= 10;
                result = Math.round(number);
                result /= 10;
                break;
            case ROUND_QUARTER:
                if ((delta >= 0) && (delta < 0.125f)) {
                    delta = 0.00f;
                } else if ((delta >= 0.125f) && (delta < 0.375f)) {
                    delta = 0.25f;
                } else if ((delta >= 0.375f) && (delta < 0.625)) {
                    delta = 0.50f;
                } else if ((delta >= 0.625f) && (delta < 0.875f)) {
                    delta = 0.75f;
                } else {
                    delta = 1.00f;
                }
                result = base + delta;
                break;
            case ROUND_EIGHTH:
                if ((delta >= 0f) && (delta < 0.0625f)) {
                    delta = 0.0f;
                } else if ((delta >= 0.0625f) && (delta < 0.1875f)) {
                    delta = 0.125f;
                } else if ((delta >= 0.1875f) && (delta < 0.3125f)) {
                    delta = 0.25f;
                } else if ((delta >= 0.3125f) && (delta < 0.4375f)) {
                    delta = 0.375f;
                } else if ((delta >= 0.4375f) && (delta < 0.5625f)) {
                    delta = 0.5f;
                } else if ((delta >= 0.5625f) && (delta < 0.6875f)) {
                    delta = 0.625f;
                } else if ((delta >= 0.6875f) && (delta < 0.8125f)) {
                    delta = 0.75f;
                } else if ((delta >= 0.8125f) && (delta < 0.9375f)) {
                    delta = 0.875f;
                } else {
                    delta = 1.0f;
                }
                result = base + delta;
                break;
        }

        return result;
    }
}
