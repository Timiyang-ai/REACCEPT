public void persist(SharedPreferences preferences) {
        final SharedPreferences.Editor edit = preferences.edit();

        edit.putBoolean(Constants.PREFS_SCHEDULES_ENABLED + id, enabled);
        edit.putInt(Constants.PREFS_SCHEDULES_HOUROFDAY + id, hour);
        edit.putInt(Constants.PREFS_SCHEDULES_REPEATTIME + id, interval);
        edit.putLong(Constants.PREFS_SCHEDULES_TIMEPLACED + id, placed);
        edit.putInt(Constants.PREFS_SCHEDULES_MODE + id, mode.value);
        edit.putInt(Constants.PREFS_SCHEDULES_SUBMODE + id, submode.value);
        edit.putBoolean(Constants.PREFS_SCHEDULES_EXCLUDESYSTEM + id,
            excludeSystem);

        edit.apply();
    }