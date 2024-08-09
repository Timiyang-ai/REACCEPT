public String getString(HazelcastProperty groupProperty) {
        return properties[groupProperty.getIndex()];
    }