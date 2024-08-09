@Override public V remove(Object key) {
        if (key == null) {
            return removeNullKey();
        }
        int hash = Collections.secondaryHash(key);
        HashMapEntry<K, V>[] tab = table;
        int index = hash & (tab.length - 1);
        for (HashMapEntry<K, V> e = tab[index], prev = null;
                e != null; prev = e, e = e.next) {
            if (e.hash == hash && key.equals(e.key)) {
                if (prev == null) {
                    tab[index] = e.next;
                } else {
                    prev.next = e.next;
                }
                modCount++;
                size--;
                postRemove(e);
                return e.value;
            }
        }
        return null;
    }