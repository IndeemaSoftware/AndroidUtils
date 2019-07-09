package com.indeema.library.androidutils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods for working with files.
 */


public class FileUtils {

    private final static String TAG = FileUtils.class.getSimpleName();

    final private static String IMAGE_PATTERN = ".*\\.(?:png|jpg|jpeg|bmp)$";
    final private static String PDF_PATTERN = ".*\\.(?:pdf)$";

    public FileUtils() {
    }

    /**
     * Getting type-safe name for Image File by Uri
     */
    public static String getFileName(String filePath) {
        Uri uri = Uri.parse(filePath);
        return uri.getLastPathSegment();
    }

    public static boolean isImage(String filePath) {
        return filePath.toLowerCase().matches(IMAGE_PATTERN);
    }

    public static boolean isPdf(String filePath) {
        return filePath.matches(PDF_PATTERN);
    }

    public static File saveBitmapToFolder(String directoryPath,
                                          String fileName,
                                          @NonNull Bitmap bmp,
                                          int quality) {

        File file = createFile(directoryPath, fileName);
        file = saveBitmapToFile(file, bmp, quality);
        return file;
    }

    public static File createFile(String directoryPath, String fileName) {
        File directoryPathFile = new File(directoryPath);
        if (!directoryPathFile.isDirectory()) { //inverse
            boolean isCreatedDir = directoryPathFile.mkdir();
            if (!isCreatedDir) { //inverse
                return null;
            }
        }

        File file = new File(directoryPathFile, fileName);
        if (file.exists()) {
            boolean isCreatedFile = file.delete();
            if (!isCreatedFile) {//inverse
                return null;
            }
        }
        return file;
    }

    public static File saveBitmapToFile(File file, Bitmap bmp, int quality) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, bytes);
        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            file = null;
        }
        return file;
    }


    public static void writeToFile(File file, String data) {
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static String removeExtension(String fileName) {
        Pattern suffix = Pattern.compile("\\.\\p{Alnum}+$");
        Matcher m = suffix.matcher(fileName);
        return m.replaceAll("");
    }

    public static String getMimeType(Context context, Uri uri) {
        String extension = null;
        if (uri.getScheme() != null && uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else if (uri.getPath() != null) {
            File file = new File(uri.getPath());
            Uri innerUri = Uri.fromFile(file);
            extension = MimeTypeMap.getFileExtensionFromUrl(innerUri.toString());
        }
        String type = null;
        if (!TextUtils.isEmpty(extension)) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }

        if (type == null) {
            type = "";
        }
        return type;
    }

    public static File createImageFile(Context context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            image = null;
        }
        return image;
    }

    public static String replaceIllegalCharacter(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
    }
}
