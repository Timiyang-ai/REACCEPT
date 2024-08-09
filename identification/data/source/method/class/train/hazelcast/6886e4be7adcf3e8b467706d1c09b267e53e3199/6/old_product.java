public int getInteger(HazelcastProperty groupProperty) {
        return Integer.parseInt(properties[groupProperty.getIndex()]);
    }