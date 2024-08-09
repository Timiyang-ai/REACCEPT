public boolean getBoolean(HazelcastProperty groupProperty) {
        return Boolean.valueOf(values[groupProperty.getIndex()]);
    }