public String getString(HazelcastProperty groupProperty) {
        return values[groupProperty.getIndex()];
    }