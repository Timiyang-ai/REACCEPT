@VisibleForTesting
    void refreshExistingShortcuts(Context context) {
        final ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        final List<ShortcutInfo> pinnedShortcuts = shortcutManager.getPinnedShortcuts();
        final List<ShortcutInfo> updates = new ArrayList<>();
        for (ShortcutInfo info : pinnedShortcuts) {
            final Intent shortcutIntent = info.getIntent();
            shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            final ShortcutInfo updatedInfo = new ShortcutInfo.Builder(context, info.getId())
                    .setIntent(shortcutIntent)
                    .build();
            updates.add(updatedInfo);
        }
        shortcutManager.updateShortcuts(updates);
    }