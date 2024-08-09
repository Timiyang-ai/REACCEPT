@Override
    public boolean add(E object) {
        return backingMap.put(object, object) == null;
    }