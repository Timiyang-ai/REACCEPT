@Test
    public void testClear() {
        // Given
        cacheManager.putFeature(new Feature("ff", false, DESCRIPTION));
        cacheManager.putFeature(new Feature("ff2", false, DESCRIPTION));
        cacheManager.putFeature(new Feature("ff3", false, DESCRIPTION));
        Assert.assertEquals(3, cacheManager.listCachedFeatureNames().size());
        // When
        cacheManager.clearFeatures();
        // Then
        Assert.assertTrue(cacheManager.listCachedFeatureNames().isEmpty());
    }