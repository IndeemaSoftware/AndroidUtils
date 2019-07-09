package com.indeema.library.androidutils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;


/**
 * Utility methods for working with dialogs.
 */

public class DialogUtils {

    public static void showDialog(Context context, String title, String message) {
        showDialog(context, title, message, context.getResources().getString(R.string.action_ok), null);
    }

    public static void showDialog(Context context, String title, String message, String positiveTitle,
                                  final DialogInterface.OnClickListener positiveBtnListener) {
        showDialog(context, title, message, positiveTitle, positiveBtnListener, null, null);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveTitle, final DialogInterface.OnClickListener positiveBtnListener,
                                  String negativeTitle, final DialogInterface.OnClickListener negativeBtnListener) {
        showDialog(context, title, message, positiveTitle, positiveBtnListener, negativeTitle, negativeBtnListener, true);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveTitle,
                                  final DialogInterface.OnClickListener positiveBtnListener,
                                  String negativeTitle,
                                  final DialogInterface.OnClickListener negativeBtnListener,
                                  boolean cancelable) {

        showDialog(context, title, message, positiveTitle, Color.DKGRAY, positiveBtnListener,
                negativeTitle, Color.DKGRAY, negativeBtnListener, cancelable);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveTitle, Integer positiveBtnColor,
                                  final DialogInterface.OnClickListener positiveBtnListener,
                                  String negativeTitle, Integer negativeBtnColor,
                                  final DialogInterface.OnClickListener negativeBtnListener,
                                  boolean cancelable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        builder.setMessage(message);
        if (!TextUtils.isEmpty(positiveTitle)) {
            builder.setPositiveButton(positiveTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (positiveBtnListener != null) positiveBtnListener.onClick(dialog, which);
                }
            });
        }
        if (!TextUtils.isEmpty(negativeTitle)) {
            builder.setNegativeButton(negativeTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (negativeBtnListener != null) negativeBtnListener.onClick(dialog, which);
                }
            });
        }
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(cancelable);
        dialog.show();

        if (positiveBtnColor != null) {
            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            if (positiveButton != null) positiveButton.setTextColor(positiveBtnColor);
        }

        if (negativeBtnColor != null) {
            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            if (negativeButton != null) negativeButton.setTextColor(negativeBtnColor);
        }
    }



}
