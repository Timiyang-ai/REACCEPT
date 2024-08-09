@Test
    public void testGeoHash() throws Exception {
        K key = keyFactory.instance();
        M v1 = valueFactory.instance();
        M v2 = valueFactory.instance();

        geoOperations.geoAdd(key, 13.361389, 38.115556, v1);
        geoOperations.geoAdd(key, 15.087269, 37.502669, v2);

        List<byte[]> result = geoOperations.geoHash(key, v1, v2);
        assertEquals(result.size(), 2);

        final RedisSerializer<String> serializer = new StringRedisSerializer();

        assertEquals(serializer.deserialize(result.get(0)), "sqc8b49rny0");
        assertEquals(serializer.deserialize(result.get(1)), "sqdtr74hyu0");
    }