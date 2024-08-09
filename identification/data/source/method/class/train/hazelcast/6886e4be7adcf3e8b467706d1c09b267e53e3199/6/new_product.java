public int getInteger(HazelcastProperty groupProperty) {
        return Integer.parseInt(values[groupProperty.getIndex()]);
    }