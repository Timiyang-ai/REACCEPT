    @Test
    public void selectWith()
    {
        Assert.assertEquals(
                FastList.newListWith(5, 4, 3, 2, 1),
                ArrayIterate.selectWith(INTEGER_ARRAY, Predicates2.instanceOf(), Integer.class));
    }