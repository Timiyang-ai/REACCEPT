    @Test
    public void forEach()
    {
        StringBuilder builder = new StringBuilder();
        StringIterate.forEach("1a2b3c", (CharProcedure) builder::append);
        Assert.assertEquals("1a2b3c", builder.toString());
    }