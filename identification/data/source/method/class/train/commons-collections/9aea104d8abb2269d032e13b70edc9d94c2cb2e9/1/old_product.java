public boolean addAsEqual(T existingObj, T newObj) {
        checkLocked();
        Integer position = map.get(existingObj);
        if (position == null) {
            throw new IllegalArgumentException(existingObj + " not known to " + this);
        }
        Integer result = map.put(newObj, position);
        return result == null;
    }