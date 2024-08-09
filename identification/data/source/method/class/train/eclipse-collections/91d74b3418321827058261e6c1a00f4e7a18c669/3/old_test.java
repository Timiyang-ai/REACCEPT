    @Test
    public void occurrencesOfCodePoint()
    {
        int count = StringIterate.occurrencesOf("1a2a3", "a".codePointAt(0));
        Assert.assertEquals(2, count);
    }