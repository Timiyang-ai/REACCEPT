    @Test
    public void select()
    {
        Iterable<Integer> iterable = new IterableAdapter<>(this.getIntegerList());
        Verify.assertSize(5, Iterate.select(iterable, Integer.class::isInstance));
        Verify.assertSize(5, Iterate.select(iterable, Integer.class::isInstance, FastList.newList()));
    }