public boolean getBoolean(HazelcastProperty property) {
        return Boolean.valueOf(getString(property));
    }