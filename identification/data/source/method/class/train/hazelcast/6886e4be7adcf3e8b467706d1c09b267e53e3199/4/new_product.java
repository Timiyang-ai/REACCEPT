public String getString(HazelcastProperty property) {
        return values[property.getIndex()];
    }