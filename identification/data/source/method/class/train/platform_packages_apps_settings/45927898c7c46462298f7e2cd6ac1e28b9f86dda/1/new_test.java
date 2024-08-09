    @Test
    public void isScreenLockVisible_shouldRespectResourceConfig() {
        for (ScreenLockType lock : ScreenLockType.values()) {
            // All locks except managed defaults to visible
            assertThat(mController.isScreenLockVisible(lock)).named(lock + " visible")
                    .isEqualTo(lock != ScreenLockType.MANAGED);
        }

        SettingsShadowResources.overrideResource(R.bool.config_hide_none_security_option, true);
        SettingsShadowResources.overrideResource(R.bool.config_hide_swipe_security_option, true);
        assertThat(mController.isScreenLockVisible(ScreenLockType.NONE)).named("NONE visible")
                .isFalse();
        assertThat(mController.isScreenLockVisible(ScreenLockType.SWIPE)).named("SWIPE visible")
                .isFalse();
    }