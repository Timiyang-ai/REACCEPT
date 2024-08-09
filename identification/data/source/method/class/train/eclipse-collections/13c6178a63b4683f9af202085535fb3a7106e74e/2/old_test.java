    @Test
    public void collectIf()
    {
        Iterable<Integer> iterable = new IterableAdapter<>(Interval.oneTo(31));
        Collection<Class<?>> result = Iterate.collectIf(iterable, Integer.valueOf(31)::equals, Object::getClass);
        Assert.assertEquals(iList(Integer.class), result);
    }