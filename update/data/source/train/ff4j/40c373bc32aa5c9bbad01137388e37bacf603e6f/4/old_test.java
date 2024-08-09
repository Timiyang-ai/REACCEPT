@Test
    public void testExistGroup() {
        Assert.assertTrue(testedStore.existGroup(G1));
        Assert.assertFalse(testedStore.existGroup(G_DOESNOTEXIST));
    }