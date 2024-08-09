    @Test
    public void queryShortcuts_shouldOnlyIncludeSystemApp() {
        final ResolveInfo ri1 = new ResolveInfo();
        ri1.activityInfo = new ActivityInfo();
        ri1.activityInfo.name = "activity1";
        ri1.activityInfo.applicationInfo = new ApplicationInfo();
        ri1.activityInfo.applicationInfo.flags = 0;
        final ResolveInfo ri2 = new ResolveInfo();
        ri2.activityInfo = new ActivityInfo();
        ri2.activityInfo.name = "activity2";
        ri2.activityInfo.applicationInfo = new ApplicationInfo();
        ri2.activityInfo.applicationInfo.flags = ApplicationInfo.FLAG_SYSTEM;

        mPackageManager.setResolveInfosForIntent(
                new Intent(CreateShortcutPreferenceController.SHORTCUT_PROBE),
                Arrays.asList(ri1, ri2));

        final List<ResolveInfo> info = mController.queryShortcuts();
        assertThat(info).hasSize(1);
        assertThat(info.get(0).activityInfo).isEqualTo(ri2.activityInfo);
    }