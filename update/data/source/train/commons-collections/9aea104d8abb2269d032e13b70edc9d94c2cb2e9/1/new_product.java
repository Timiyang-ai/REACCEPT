public boolean addAsEqual(final T existingObj, final T newObj) {
        checkLocked();
        final Integer position = map.get(existingObj);
        if (position == null) {
            throw new IllegalArgumentException(existingObj + " not known to " + this);
        }
        final Integer result = map.put(newObj, position);
        return result == null;
    }