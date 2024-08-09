public long getLong(HazelcastProperty groupProperty) {
        return Long.parseLong(values[groupProperty.getIndex()]);
    }