    @Test
    public void getOutputForTag() throws Exception {
        final List<String> happyList = setOutputForTag("happy");
        Assert.assertEquals(2, happyList.size());
        // although there are two keys that output HAPPY, they will be merged into one output.
        Assert.assertArrayEquals(
                new String[] {MAGNIFYING_GLASS_CHARACTER + "happy", "HAPPY"}, happyList.toArray());
        Assert.assertArrayEquals(
                new String[] {MAGNIFYING_GLASS_CHARACTER + "palm", "PALM"},
                setOutputForTag("palm").toArray());
    }