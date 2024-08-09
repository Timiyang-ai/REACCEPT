@Test
    public void exist_filled() {
        // When-Then
        Assert.assertTrue(testedStore.exist("a"));
        Assert.assertFalse(testedStore.exist("k"));
    }