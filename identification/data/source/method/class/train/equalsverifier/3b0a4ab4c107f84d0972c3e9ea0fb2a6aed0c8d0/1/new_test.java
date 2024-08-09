    @Test
    public void contains() {
        cache.put(STRING_CLASS, STRING_FACTORY);
        assertTrue(cache.contains(STRING_CLASS));
    }