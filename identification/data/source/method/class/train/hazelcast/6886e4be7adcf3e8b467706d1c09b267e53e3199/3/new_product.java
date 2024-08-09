public float getFloat(HazelcastProperty property) {
        return Float.valueOf(getString(property));
    }