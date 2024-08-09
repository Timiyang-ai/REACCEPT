@Override
    public boolean containsValue(final Object value) {
        if (value == null) {
            return containsNullValue();
        }

        final Entry[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
    }