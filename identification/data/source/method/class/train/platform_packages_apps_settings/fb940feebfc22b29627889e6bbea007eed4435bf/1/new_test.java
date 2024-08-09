    @Test
    public void addToScreen_addsToEnd() {
        Settings.Global.putInt(mResolver, Global.AUTOMATIC_POWER_SAVE_MODE,
                PowerManager.POWER_SAVE_MODE_TRIGGER_PERCENTAGE);
        Settings.Global.putInt(mResolver, Global.LOW_POWER_MODE_TRIGGER_LEVEL, 15);
        mController.addToScreen(mScreen);
        assertThat(mController.mSeekBarPreference.getOrder()).isEqualTo(100);
    }