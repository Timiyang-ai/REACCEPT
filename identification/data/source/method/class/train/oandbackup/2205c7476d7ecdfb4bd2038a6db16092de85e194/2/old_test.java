    @Test
    public void test_persist() throws SchedulingException {
        final Context appContext = InstrumentationRegistry.getTargetContext();
        final SharedPreferences preferences = PreferenceManager
            .getDefaultSharedPreferences(appContext);
        assertThat("clear preferences", preferences.edit().clear().commit(),
            is(true));
        final Schedule schedule = new Schedule.Builder()
            .withId(0)
            .withEnabled(true)
            .withHour(12)
            .withInterval(2)
            .withMode(3)
            .withSubmode(1)
            .withPlaced(1546100595221L)
            .build();
        schedule.persist(preferences);

        assertThat("enabled", preferences.getBoolean("enabled0", false),
            is(true));
        assertThat("hour", preferences.getInt("hourOfDay0", 0), is(12));
        assertThat("repeat time", preferences.getInt("repeatTime0", 0), is(2));
        assertThat("mode", preferences.getInt("scheduleMode0", 0), is(3));
        assertThat("submode", preferences.getInt("scheduleSubMode0", 0),
            is(1));
        assertThat("time placed", preferences.getLong("timePlaced0", 0),
            is(1546100595221L));
    }