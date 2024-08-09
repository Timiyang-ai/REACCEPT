@Override
    public Object clone() {
        KeyComparatorHashMap<K, V> result = null;

        try {
            result = (KeyComparatorHashMap<K, V>) super.clone();

            result.table = new Entry[table.length];
            result.entrySet = null;
            result.modCount = 0;
            result.size = 0;
            result.init();
            result.putAllForCreate(this);
        } catch (final CloneNotSupportedException e) {
            // assert false;
        }

        return result;
    }