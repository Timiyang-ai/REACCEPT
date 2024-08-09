public void addToScreen(PreferenceScreen screen) {
        // makes sure it gets added after the preferences if called due to first time battery
        // saver message
        mSeekBarPreference.setOrder(5);
        screen.addPreference(mSeekBarPreference);
    }