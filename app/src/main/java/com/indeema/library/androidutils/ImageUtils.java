package com.indeema.library.androidutils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility methods for working with images.
 */


public class ImageUtils {

    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 16.0f;


    /**
     * <p>Make screenshot from view</p>
     *
     * @param v the view, not altered, not null.
     * @return bitmap with from view
     */
    public static Bitmap getScreenshot(@NonNull View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    /**
     * <p>Make rotate of bitmap</p>
     *
     * @param source the bitmap, not altered, not null.
     * @param angle  at which the bitmap will be returned
     * @return rotated bitmap
     */
    public static Bitmap rotateBitmap(@NonNull Bitmap source, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(360 - angle);
        return Bitmap.createBitmap(source,
                0,
                0,
                source.getWidth(),
                source.getHeight(),
                matrix,
                true);
    }

    /**
     * <p>Make mirror of bitmap</p>
     *
     * @param source the bitmap, not altered, not null.
     * @return mirrored bitmap
     */
    public static Bitmap mirrorBitmap(Bitmap source) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, false);
    }

    /**
     * <p>Load a scaled down version into memory</p>
     *
     * @param res       class of resource, not null
     * @param resId     resource which should scale
     * @param reqWidth  the the required width.
     * @param reqHeight the the required height.
     * @return scaled bitmap
     */
    public static Bitmap decodeSampledBitmapFromResource(@NonNull Resources res,
                                                         int resId,
                                                         int reqWidth,
                                                         int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * This is a method for load a scaled down version into memory
     */

    /**
     * <p>Load a scaled down version into memory</p>
     *
     * @param data          array of data
     * @param reqWidth      the the required width.
     * @param reqHeight     the the required height.
     * @param rotationAngle at which the bitmap will be returned
     * @return null if data is to big, (outOfMemory) if else return scaled bitmap with rotate
     */
    public static Bitmap decodeSampledBitmapResource(byte[] data,
                                                     int reqWidth,
                                                     int reqHeight,
                                                     int rotationAngle) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        if (reqWidth == -1) reqWidth = options.outWidth;
        if (reqHeight == -1) reqHeight = options.outHeight;

        float ratio = (float) options.outWidth / (float) options.outHeight;
        if (reqHeight == 0 && reqWidth > 0) reqHeight = (int) (reqWidth / ratio);
        if (reqWidth == 0 && reqHeight > 0) reqWidth = (int) (reqHeight * ratio);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        Bitmap returnedBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);

        if (returnedBitmap.getWidth() > returnedBitmap.getHeight())
            returnedBitmap = rotateBitmap(returnedBitmap, rotationAngle);

        returnedBitmap = scaleCenterCrop(returnedBitmap, returnedBitmap.getWidth(), returnedBitmap.getWidth());
        try {
            return mirrorBitmap(returnedBitmap);
        } catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
    }

    /**
     * <p>calculate a sample size value that is a power of two based on a target width and height</p>
     *
     * @param options   bitmapFactory, not altered, not null.
     * @param reqWidth  the the required width.
     * @param reqHeight the the required height.
     * @return sample size value that is a power of two based on a target width and height
     */
    public static int calculateInSampleSize(@NonNull BitmapFactory.Options options,
                                            int reqWidth,
                                            int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    || (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * <p>Scale bitmap with centerCrop type</p>
     *
     * @param source    the bitmap, not altered, not null.
     * @param newWidth  the the required width.
     * @param newHeight the the required height.
     * @return bitmap scaled to width and hight
     */
    public static Bitmap scaleCenterCrop(Bitmap source, int newWidth, int newHeight) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }

    /**
     * <p>Trim bitmap to square</p>
     *
     * @param src the bitmap, not altered, not null.
     * @return square to biggest side bitmap
     */
    public static Bitmap trimToSquare(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        int crop = (width - height) / 2;
        if (width >= height) {
            src = Bitmap.createBitmap(src, crop, 0, height, height);
        } else {
            crop = (height - width) / 2;
            src = Bitmap.createBitmap(src, 0, crop, width, width);
        }
        return src;
    }

    /**
     * <p>Save bitmap to file</p>
     *
     * @param name the name of bitmap
     * @param bmp  the bitmap, not altered, not null.
     * @return return file or null when unsuccessful creating file
     * @throws IOException
     */
    public static File saveBitmapToFile(String name, Bitmap bmp) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + name + ".jpg");

        boolean isSuccess = file.createNewFile();
        if (isSuccess) {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
            return file;

        } else {
            return null;
        }
    }
}
