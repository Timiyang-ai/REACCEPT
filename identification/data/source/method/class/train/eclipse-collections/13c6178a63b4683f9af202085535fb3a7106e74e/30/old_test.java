    @Test
    public void collect()
    {
        Iterable<Boolean> iterable = new IterableAdapter<>(iList(Boolean.TRUE, Boolean.FALSE, null));
        Collection<String> result = Iterate.collect(iterable, String::valueOf);
        Assert.assertEquals(iList("true", "false", "null"), result);
    }