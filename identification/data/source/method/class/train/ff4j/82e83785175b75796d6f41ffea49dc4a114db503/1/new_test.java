@Test
    public void exist_filled() {
        // When-Then
        Assert.assertTrue(testedStore.existProperty("a"));
        Assert.assertFalse(testedStore.existProperty("k"));
    }