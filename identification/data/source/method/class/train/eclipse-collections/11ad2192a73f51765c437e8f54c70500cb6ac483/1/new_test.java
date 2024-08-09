    @Test
    public void chunk()
    {
        String[] array = {"1", "2", "3", "4", "5", "6", "7"};
        RichIterable<RichIterable<String>> groups = ArrayIterate.chunk(array, 2);
        Assert.assertEquals(
                Lists.immutable.with(
                        Lists.immutable.with("1", "2"),
                        Lists.immutable.with("3", "4"),
                        Lists.immutable.with("5", "6"),
                        Lists.immutable.with("7")),
                groups);
    }