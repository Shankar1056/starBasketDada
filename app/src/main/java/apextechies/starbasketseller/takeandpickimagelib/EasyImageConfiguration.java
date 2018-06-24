package apextechies.starbasketseller.takeandpickimagelib;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * @author Shankar <shankar@spotsoon.com>
 * @created on 27 Oct 2017 at 3:28 PM
 */

public class EasyImageConfiguration implements Constants {

    private Context context;

    EasyImageConfiguration(Context context) {
        this.context = context;
    }

    public EasyImageConfiguration setImagesFolderName() {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(BundleKeys.FOLDER_NAME, "EasyImage sample")
                .commit();
        return this;
    }

    public EasyImageConfiguration setAllowMultiplePickInGallery() {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(BundleKeys.ALLOW_MULTIPLE, true)
                .commit();
        return this;
    }

    public EasyImageConfiguration setCopyTakenPhotosToPublicGalleryAppFolder() {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(BundleKeys.COPY_TAKEN_PHOTOS, true)
                .commit();
        return this;
    }

    public EasyImageConfiguration setCopyPickedImagesToPublicGalleryAppFolder() {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(BundleKeys.COPY_PICKED_IMAGES, true)
                .commit();
        return this;
    }

    public String getFolderName() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(BundleKeys.FOLDER_NAME, DEFAULT_FOLDER_NAME);
    }

    public boolean allowsMultiplePickingInGallery() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.ALLOW_MULTIPLE, false);
    }

    public boolean shouldCopyTakenPhotosToPublicGalleryAppFolder() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.COPY_TAKEN_PHOTOS, false);
    }

    public boolean shouldCopyPickedImagesToPublicGalleryAppFolder() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.COPY_PICKED_IMAGES, false);
    }

}
