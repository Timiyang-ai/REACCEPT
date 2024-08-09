@Test
    public void testReadGroup() {
        Map<String, Feature> group = testedStore.readGroup(G1);
        Assert.assertEquals(2, group.size());
        Assert.assertTrue(group.containsKey(F3));
        Assert.assertTrue(group.containsKey(F4));
    }