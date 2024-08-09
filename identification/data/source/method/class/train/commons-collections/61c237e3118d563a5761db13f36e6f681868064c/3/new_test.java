    @SuppressWarnings("unchecked")
    public void test_containsValue_nullMatchesIncorrectly() {
        final StaticBucketMap<K, V> map = new StaticBucketMap<>(17);
        map.put((K) "A", null);
        assertEquals(true, map.containsValue(null));
        // loop so we find a string that is in the same bucket as the null
        for (int i = 'A'; i <= 'Z'; i++) {
            final String str = String.valueOf((char) i);
            assertEquals("String: " + str, false, map.containsValue(str));
        }
    }