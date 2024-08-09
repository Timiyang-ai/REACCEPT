    @Test
    public void test_fromPreferences() throws SchedulingException {
        final Context appContext = InstrumentationRegistry.getTargetContext();
        final SharedPreferences preferences = PreferenceManager
            .getDefaultSharedPreferences(appContext);
        final SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("enabled0", true);
        edit.putInt("hourOfDay0", 12);
        edit.putInt("repeatTime0", 2);
        edit.putInt("scheduleMode0", 1);
        edit.putInt("scheduleSubMode0", 0);
        edit.putLong("timePlaced0", 1546100595221L);
        edit.putLong("timeUntilNextEvent0", 1500L);
        edit.commit();

        final Schedule schedule = Schedule.fromPreferences(
            preferences, 0);
        assertThat("enabled", schedule.isEnabled(), is(true));
        assertThat("hour", schedule.getHour(), is(12));
        assertThat("repeat time", schedule.getInterval(), is(2));
        assertThat("mode", schedule.getMode(), is(Schedule.Mode.USER));
        assertThat("submode", schedule.getSubmode(), is(Schedule.Submode.APK));
        assertThat("placed", schedule.getPlaced(), is(1546100595221L));
    }