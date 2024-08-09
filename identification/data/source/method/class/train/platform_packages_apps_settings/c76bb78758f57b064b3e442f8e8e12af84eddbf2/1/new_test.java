    @Test
    public void updateAnomalies_updateSuccessfully() {
        mBatteryDatabaseManager.insertAnomaly(UID_NEW, PACKAGE_NAME_NEW, TYPE_NEW,
                AnomalyDatabaseHelper.State.NEW, NOW);
        mBatteryDatabaseManager.insertAnomaly(UID_OLD, PACKAGE_NAME_OLD, TYPE_OLD,
                AnomalyDatabaseHelper.State.NEW, NOW);
        final AppInfo appInfo = new AppInfo.Builder().setPackageName(PACKAGE_NAME_OLD).build();
        final List<AppInfo> updateAppInfos = new ArrayList<>();
        updateAppInfos.add(appInfo);

        // Change state of PACKAGE_NAME_OLD to handled
        mBatteryDatabaseManager.updateAnomalies(updateAppInfos,
                AnomalyDatabaseHelper.State.HANDLED);

        // The state of PACKAGE_NAME_NEW is still new
        List<AppInfo> newAppInfos = mBatteryDatabaseManager.queryAllAnomalies(ONE_DAY_BEFORE,
                AnomalyDatabaseHelper.State.NEW);
        assertThat(newAppInfos).containsExactly(mNewAppInfo);

        // The state of PACKAGE_NAME_OLD is changed to handled
        List<AppInfo> handledAppInfos = mBatteryDatabaseManager.queryAllAnomalies(ONE_DAY_BEFORE,
                AnomalyDatabaseHelper.State.HANDLED);
        assertThat(handledAppInfos).containsExactly(mOldAppInfo);
    }