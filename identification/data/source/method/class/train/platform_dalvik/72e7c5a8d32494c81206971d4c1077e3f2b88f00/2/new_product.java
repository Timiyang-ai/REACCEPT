@Override
    public boolean add(E object) {
        return backingMap.put(object, Boolean.TRUE) == null;
    }