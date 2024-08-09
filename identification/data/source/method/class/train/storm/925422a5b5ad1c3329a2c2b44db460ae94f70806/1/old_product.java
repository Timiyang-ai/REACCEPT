public int fieldIndex(String field) {
        Integer ret = _index.get(field);
        if (ret == null) {
            throw new IllegalArgumentException(field + " does not exist");
        }
        return ret;
    }