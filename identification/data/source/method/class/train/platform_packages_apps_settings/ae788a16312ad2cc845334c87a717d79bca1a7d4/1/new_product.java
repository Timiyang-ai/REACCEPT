@VisibleForTesting
    Intent createResultIntent(Intent shortcutIntent, ResolveInfo resolveInfo,
            CharSequence label) {
        ShortcutInfo info = createShortcutInfo(mContext, shortcutIntent, resolveInfo, label);
        Intent intent = mShortcutManager.createShortcutResultIntent(info);
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(mContext, R.mipmap.ic_launcher_settings))
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
                .putExtra(Intent.EXTRA_SHORTCUT_NAME, label);

        final ActivityInfo activityInfo = resolveInfo.activityInfo;
        if (activityInfo.icon != 0) {
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, createIcon(
                    mContext,
                    activityInfo.applicationInfo,
                    activityInfo.icon,
                    R.layout.shortcut_badge,
                    mContext.getResources().getDimensionPixelSize(R.dimen.shortcut_size)));
        }
        return intent;
    }