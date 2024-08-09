    @Test
    public void createResultIntent() {
        when(mShortcutManager.createShortcutResultIntent(any(ShortcutInfo.class)))
                .thenReturn(new Intent().putExtra("d1", "d2"));

        final Intent intent = new Intent(CreateShortcutPreferenceController.SHORTCUT_PROBE)
                .setClass(mContext, Settings.ManageApplicationsActivity.class);
        final ResolveInfo ri = mContext.getPackageManager().resolveActivity(intent, 0);
        final Intent result = mController.createResultIntent(intent, ri, "dummy");

        assertThat(result.getStringExtra("d1")).isEqualTo("d2");
        assertThat((Object) result.getParcelableExtra(Intent.EXTRA_SHORTCUT_INTENT)).isNotNull();

        ArgumentCaptor<ShortcutInfo> infoCaptor = ArgumentCaptor.forClass(ShortcutInfo.class);
        verify(mShortcutManager, times(1))
                .createShortcutResultIntent(infoCaptor.capture());
        assertThat(infoCaptor.getValue().getId())
                .isEqualTo(SHORTCUT_ID_PREFIX + intent.getComponent().flattenToShortString());
    }