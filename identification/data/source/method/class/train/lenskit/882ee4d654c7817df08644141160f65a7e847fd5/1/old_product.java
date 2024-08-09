public double get(VectorEntry entry) {
        final SparseVector evec = entry.getVector();
        final int eind = entry.getIndex();

        if (evec == null) {
            throw new IllegalArgumentException("entry is not associated with a vector");
        } else if (evec.keys != this.keys) {
            throw new IllegalArgumentException("entry does not have safe key domain");
        } else if (entry.getKey() != keys[eind]) {
            throw new IllegalArgumentException("entry does not have the correct key for its index");
        }
        if (usedKeys.get(eind)) {
            return values[eind];
        } else {
            return Double.NaN;
        }
    }