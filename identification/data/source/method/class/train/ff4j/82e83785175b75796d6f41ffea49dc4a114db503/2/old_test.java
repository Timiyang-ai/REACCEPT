@Test
    public void testClear() {
        // Given
        cacheManager.put(new Feature("ff", false, "Description"));
        cacheManager.put(new Feature("ff2", false, "Description"));
        cacheManager.put(new Feature("ff3", false, "Description"));
        Assert.assertEquals(3, cacheManager.listCachedFeatureNames().size());
        // When
        cacheManager.clear();
        // Then
        Assert.assertTrue(cacheManager.listCachedFeatureNames().isEmpty());
    }