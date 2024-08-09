public float getFloat(HazelcastProperty property) {
        return Float.valueOf(values[property.getIndex()]);
    }