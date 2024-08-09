@VisibleForTesting
    Intent createResultIntent(Intent shortcutIntent, ResolveInfo resolveInfo,
            CharSequence label) {
        final ActivityInfo activityInfo = resolveInfo.activityInfo;

        final Icon maskableIcon;
        if (activityInfo.icon != 0 && activityInfo.applicationInfo != null) {
            maskableIcon = Icon.createWithAdaptiveBitmap(createIcon(
                    activityInfo.applicationInfo, activityInfo.icon,
                    R.layout.shortcut_badge_maskable,
                    mContext.getResources().getDimensionPixelSize(R.dimen.shortcut_size_maskable)));
        } else {
            maskableIcon = Icon.createWithResource(mContext, R.drawable.ic_launcher_settings);
        }
        final String shortcutId = SHORTCUT_ID_PREFIX +
                shortcutIntent.getComponent().flattenToShortString();
        ShortcutInfo info = new ShortcutInfo.Builder(mContext, shortcutId)
                .setShortLabel(label)
                .setIntent(shortcutIntent)
                .setIcon(maskableIcon)
                .build();
        Intent intent = mShortcutManager.createShortcutResultIntent(info);
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(mContext, R.mipmap.ic_launcher_settings))
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
                .putExtra(Intent.EXTRA_SHORTCUT_NAME, label);

        if (activityInfo.icon != 0) {
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, createIcon(
                    activityInfo.applicationInfo,
                    activityInfo.icon,
                    R.layout.shortcut_badge,
                    mContext.getResources().getDimensionPixelSize(R.dimen.shortcut_size)));
        }
        return intent;
    }