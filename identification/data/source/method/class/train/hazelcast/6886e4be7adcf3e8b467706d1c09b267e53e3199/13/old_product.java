public boolean getBoolean(HazelcastProperty groupProperty) {
        return Boolean.valueOf(properties[groupProperty.getIndex()]);
    }