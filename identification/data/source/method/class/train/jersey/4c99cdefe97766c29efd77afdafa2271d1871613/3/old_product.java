@Override
    public boolean containsValue(Object value) {
        if (value == null) {
            return containsNullValue();
        }

        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
    }