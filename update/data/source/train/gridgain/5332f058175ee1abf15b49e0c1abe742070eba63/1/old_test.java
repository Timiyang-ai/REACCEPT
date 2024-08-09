@Test
    public void testLocalPeek() throws Exception {
        if (cacheMode() == LOCAL) {
            checkAffinityLocalCache();

            checkStorage(0);
        }
        else {
            checkAffinityPeek(0);

            checkAffinityPeek(1);

            checkStorage(0);

            checkStorage(1);
        }
    }