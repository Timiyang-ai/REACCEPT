@Override
    public Object clone() {
        KeyComparatorHashMap<K, V> result = null;
        try {
            result = (KeyComparatorHashMap<K, V>) super.clone();
        } catch (CloneNotSupportedException e) {
            // assert false;
        }
        result.table = new Entry[table.length];
        result.entrySet = null;
        result.modCount = 0;
        result.size = 0;
        result.init();
        result.putAllForCreate(this);

        return result;
    }