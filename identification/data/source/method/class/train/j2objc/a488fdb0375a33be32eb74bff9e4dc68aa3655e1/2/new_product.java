public Collection<V> values() {
        if (valuesCollection == null) {
            valuesCollection = new ValuesCollection();
        }
        return valuesCollection;
    }