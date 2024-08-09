    @Test
    public void createPreferenceControllers_matchExpectedControllers() {
        TimeZoneSettings settings = new TimeZoneSettings();
        List<AbstractPreferenceController> controllers =
                settings.createPreferenceControllers(RuntimeEnvironment.application);
        assertThat(controllers).hasSize(3);
        assertThat(controllers.get(0)).isInstanceOf(RegionPreferenceController.class);
        assertThat(controllers.get(1)).isInstanceOf(RegionZonePreferenceController.class);
        assertThat(controllers.get(2)).isInstanceOf(FixedOffsetPreferenceController.class);
    }