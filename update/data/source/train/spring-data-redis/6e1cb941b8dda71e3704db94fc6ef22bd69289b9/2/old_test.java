@Test
    public void testGeoRemove(){
        K key = keyFactory.instance();
        M v1 = valueFactory.instance();
        Long numAdded = geoOperations.geoAdd(key, 13.361389, 38.115556, v1);
        assertEquals(numAdded.longValue(), 1L);

        Long numRemoved = geoOperations.geoRemove(key, v1);
        assertEquals(1L, numRemoved.longValue());
    }