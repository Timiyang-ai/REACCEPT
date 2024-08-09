    @Test
    public void count()
    {
        int count = StringIterate.count("1a2a3", CharPredicates.isDigit());
        Assert.assertEquals(3, count);
    }