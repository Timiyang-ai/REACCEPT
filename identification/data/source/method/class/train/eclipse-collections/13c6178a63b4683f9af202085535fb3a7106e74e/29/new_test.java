    @Test
    public void collect()
    {
        Boolean[] objectArray = {true, false, null};
        Assert.assertEquals(
                iList("true", "false", "null"),
                ArrayIterate.collect(objectArray, String::valueOf));
    }