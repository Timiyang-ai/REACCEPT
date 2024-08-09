    @Test
    public void reject()
    {
        Assert.assertEquals(
                FastList.newListWith(5, 4, 3, 2, 1),
                ArrayIterate.reject(INTEGER_ARRAY, String.class::isInstance));
    }