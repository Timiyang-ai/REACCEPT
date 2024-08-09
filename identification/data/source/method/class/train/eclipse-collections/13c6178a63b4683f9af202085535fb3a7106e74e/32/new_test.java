    @Test
    public void select()
    {
        Assert.assertEquals(
                FastList.newListWith(5, 4, 3, 2, 1),
                ArrayIterate.select(INTEGER_ARRAY, Integer.class::isInstance));
    }