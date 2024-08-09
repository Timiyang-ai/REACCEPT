@VisibleForTesting
    List<ResolveInfo> queryShortcuts() {
        final List<ResolveInfo> shortcuts = new ArrayList<>();
        final List<ResolveInfo> activities = mPackageManager.queryIntentActivities(SHORTCUT_PROBE,
                PackageManager.GET_META_DATA);

        if (activities == null) {
            return null;
        }
        for (ResolveInfo info : activities) {
            if (info.activityInfo.name.endsWith(TetherSettingsActivity.class.getSimpleName())) {
                if (!mConnectivityManager.isTetheringSupported()) {
                    continue;
                }
            }
            if (!info.activityInfo.applicationInfo.isSystemApp()) {
                Log.d(TAG, "Skipping non-system app: " + info.activityInfo);
                continue;
            }
            shortcuts.add(info);
        }
        return shortcuts;
    }