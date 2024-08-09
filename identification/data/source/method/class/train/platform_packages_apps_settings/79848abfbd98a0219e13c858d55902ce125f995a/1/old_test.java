    @Test
    public void refreshExistingShortcuts_shouldUpdateLaunchIntentFlagsForExistingShortcut() {
        final String id = "test_shortcut_id";
        final Intent intent = new Intent(Intent.ACTION_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        final ShortcutInfo info = new ShortcutInfo.Builder(mContext, id)
                .setShortLabel("test123")
                .setIntent(intent)
                .build();

        mShortcutManager.addDynamicShortcuts(Collections.singletonList(info));
        mShortcutManager.requestPinShortcut(info, null);

        mSettingsInitialize.refreshExistingShortcuts(mContext);

        final List<ShortcutInfo> updatedShortcuts = mShortcutManager.getPinnedShortcuts();
        assertThat(updatedShortcuts).hasSize(1);
        final ShortcutInfo updateInfo = updatedShortcuts.get(0);
        assertThat(updateInfo.getId()).isEqualTo(id);
        final int flags = updateInfo.getIntent().getFlags();
        // The original flag should be removed
        assertThat(flags & Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED).isEqualTo(0);
        // The new flags should be set
        assertThat(flags & Intent.FLAG_ACTIVITY_NEW_TASK).isEqualTo(Intent.FLAG_ACTIVITY_NEW_TASK);
        assertThat(flags & Intent.FLAG_ACTIVITY_CLEAR_TOP).isEqualTo(
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }