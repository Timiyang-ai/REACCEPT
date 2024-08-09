    @Test
    public void collect()
    {
        Assert.assertEquals(
                iList("true", "false", "null"),
                RandomAccessListIterate.collect(mList(true, false, null), String::valueOf));
    }