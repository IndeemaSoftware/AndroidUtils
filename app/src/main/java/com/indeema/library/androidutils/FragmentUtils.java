package com.indeema.library.androidutils;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;


/**
 * Utility methods for working with fragments.
 */

public class FragmentUtils {

    public static final int ANIMATION_NONE = 0;
    public static final int ANIMATION_FORWARD = 1;
    public static final int ANIMATION_BACK = 2;

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment, int layoutId) {
        String tag = fragment.getClass().getSimpleName();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(layoutId, fragment, tag);
            transaction.commit();
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int layoutId, int animation, boolean addToBackStack) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (animation) {
            case ANIMATION_FORWARD:
                transaction.setCustomAnimations(
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit,
                        R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit);
                break;
            case ANIMATION_BACK:
                transaction.setCustomAnimations(
                        R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                break;
        }
        transaction.replace(layoutId, fragment, tag);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void replaceFragmentSharedElement(FragmentManager fragmentManager, Fragment fragment, int layoutId, boolean addToBackStack, List<SharedElement> sharedElements) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layoutId, fragment, tag);
        if (addToBackStack) transaction.addToBackStack(null);
        if (sharedElements != null && sharedElements.size() > 0) {
            for (SharedElement sharedElement : sharedElements) {
                transaction.addSharedElement(sharedElement.getSharedElement(), sharedElement.getName());
            }
        }
        transaction.commit();
    }


    public static void setFragmentWithBackAnimation(FragmentManager fragmentManager, Fragment fragment, int layoutResIs, boolean addToBackStack) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(layoutResIs, fragment, tag);
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void setFragmentWithAnimation(FragmentManager fragmentManager, Fragment fragment, int layoutResIs, boolean addToBackStack) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit,
                R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit);
        fragmentTransaction.replace(layoutResIs, fragment, tag);
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public class SharedElement {

        private View mSharedElement;
        private String mName;

        public SharedElement() {

        }

        public SharedElement(View sharedElement, String name) {
            mSharedElement = sharedElement;
            mName = name;
        }

        public View getSharedElement() {
            return mSharedElement;
        }

        public void setSharedElement(View sharedElement) {
            mSharedElement = sharedElement;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }
    }
}
