public float getFloat(HazelcastProperty groupProperty) {
        return Float.valueOf(values[groupProperty.getIndex()]);
    }