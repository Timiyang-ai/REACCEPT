@Override
    public boolean existProperty(String name) {
        Util.assertHasLength(name);
        return properties.containsKey(name);
    }