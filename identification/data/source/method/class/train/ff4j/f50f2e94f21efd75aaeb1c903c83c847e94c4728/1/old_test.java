@Test
    public void testClear() {
        // Given
        cacheManager.putFeature(new Feature("ff", false, "Description"));
        cacheManager.putFeature(new Feature("ff2", false, "Description"));
        cacheManager.putFeature(new Feature("ff3", false, "Description"));
        Assert.assertEquals(3, cacheManager.listCachedFeatureNames().size());
        // When
        cacheManager.clearFeatures();
        // Then
        Assert.assertTrue(cacheManager.listCachedFeatureNames().isEmpty());
    }