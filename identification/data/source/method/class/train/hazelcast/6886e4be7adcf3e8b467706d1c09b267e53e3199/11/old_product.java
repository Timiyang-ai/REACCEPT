public long getLong(HazelcastProperty groupProperty) {
        return Long.parseLong(properties[groupProperty.getIndex()]);
    }