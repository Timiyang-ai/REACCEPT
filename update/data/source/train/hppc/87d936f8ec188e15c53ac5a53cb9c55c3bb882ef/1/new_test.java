@Test
    public void testPutAll()
    {
        map.put(key1, value1);
        map.put(key2, value1);

        KTypeVTypeOpenHashMap<KType, VType> map2 = 
            new KTypeVTypeOpenHashMap<KType, VType>();

        map2.put(key2, value2);
        map2.put(keyE, value1);

        // One new key (keyE).
        assertEquals(1, map.putAll(map2));
        
        // Assert the value under key2 has been replaced.
        assertEquals2(value2, map.get(key2));

        // And key3 has been added.
        assertEquals2(value1, map.get(keyE));
        assertEquals(3, map.size());
    }