    @Test
    public void reject()
    {
        Iterable<Integer> iterable = new IterableAdapter<>(this.getIntegerList());
        Verify.assertSize(5, Iterate.reject(iterable, String.class::isInstance));
        Verify.assertSize(
                5,
                Iterate.reject(iterable, String.class::isInstance, FastList.newList()));
    }