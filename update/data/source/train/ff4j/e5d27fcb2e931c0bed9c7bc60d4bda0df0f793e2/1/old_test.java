@Test
    public void readAllProperties() {
        // Given
        Assert.assertNotNull(testedStore);
        // When
        Map <String, AbstractProperty<?>> mapsOf = testedStore.readAllProperties();
        // When
        Assert.assertTrue(mapsOf.containsKey("a"));
        Assert.assertTrue(mapsOf.containsKey("b"));
    }