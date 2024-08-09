public long getLong(HazelcastProperty property) {
        return Long.parseLong(getString(property));
    }