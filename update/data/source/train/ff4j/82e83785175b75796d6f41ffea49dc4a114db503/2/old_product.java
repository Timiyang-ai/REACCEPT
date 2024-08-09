@Override
    public boolean exist(String name) {
        Util.assertHasLength(name);
        return properties.containsKey(name);
    }