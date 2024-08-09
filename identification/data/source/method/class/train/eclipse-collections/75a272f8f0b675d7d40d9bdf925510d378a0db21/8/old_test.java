    @Test
    public void count()
    {
        MutableMap<String, Integer> map = this.getIntegerMap();
        Assert.assertEquals(5, MapIterate.count(map, Integer.class::isInstance));
    }