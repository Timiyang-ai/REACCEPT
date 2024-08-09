    @SuppressWarnings("unchecked")
    public void test_containsKey_nullMatchesIncorrectly() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put(null, (V) "A");
        assertEquals(true, map.containsKey(null));
        // loop so we find a string that is in the same bucket as the null
        for (int i = 'A'; i <= 'Z'; i++) {
            final String str = String.valueOf((char) i);
            assertEquals("String: " + str, false, map.containsKey(str));
        }
    }