public int getInteger(HazelcastProperty property) {
        return Integer.parseInt(values[property.getIndex()]);
    }