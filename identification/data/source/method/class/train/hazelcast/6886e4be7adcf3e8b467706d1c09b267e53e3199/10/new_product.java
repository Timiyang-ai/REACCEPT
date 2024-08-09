public long getLong(HazelcastProperty property) {
        return Long.parseLong(values[property.getIndex()]);
    }