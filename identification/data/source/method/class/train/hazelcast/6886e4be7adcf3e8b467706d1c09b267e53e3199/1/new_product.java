public boolean getBoolean(HazelcastProperty property) {
        return Boolean.valueOf(values[property.getIndex()]);
    }