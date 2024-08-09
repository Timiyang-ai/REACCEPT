    private void remove(int k, int v, boolean present) {
        assertEquals(present, map.remove(k, v));
        referenceMap.remove(k, v);
    }