public static Schedule fromPreferences(SharedPreferences preferences,
            long number) throws SchedulingException {
        final Schedule schedule = new Schedule();
        schedule.id = number;
        schedule.enabled = preferences.getBoolean(
            Constants.PREFS_SCHEDULES_ENABLED + number, false);
        schedule.hour = preferences.getInt(
            Constants.PREFS_SCHEDULES_HOUROFDAY + number, 0);
        schedule.interval = preferences.getInt(
            Constants.PREFS_SCHEDULES_REPEATTIME + number, 0);
        schedule.placed = preferences.getLong(
            Constants.PREFS_SCHEDULES_TIMEPLACED + number, 0);
        schedule.mode = Mode.intToMode(preferences.getInt(
            Constants.PREFS_SCHEDULES_MODE + number, 0));
        schedule.submode = Submode.intToSubmode(preferences.getInt(
            Constants.PREFS_SCHEDULES_SUBMODE + number, 0));
        schedule.timeUntilNextEvent = preferences.getLong(
            Constants.PREFS_SCHEDULES_TIMEUNTILNEXTEVENT + number, 0);
        schedule.excludeSystem = preferences.getBoolean(
            Constants.PREFS_SCHEDULES_EXCLUDESYSTEM + number, false);
        return schedule;
    }