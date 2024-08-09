    @Test
    public void collect()
    {
        Assert.assertEquals("ABC", StringIterate.collect("abc", CharToCharFunctions.toUpperCase()));
        Assert.assertEquals("abc", StringIterate.collect("abc", CharToCharFunctions.toLowerCase()));
    }