public float getFloat(HazelcastProperty groupProperty) {
        return Float.valueOf(properties[groupProperty.getIndex()]);
    }