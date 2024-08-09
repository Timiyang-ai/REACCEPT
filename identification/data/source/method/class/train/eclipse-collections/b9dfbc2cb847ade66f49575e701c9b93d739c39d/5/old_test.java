    @Override
    @Test
    public void newEmpty()
    {
        Verify.assertInstanceOf(FastList.class, CollectionAdapter.adapt(new LinkedList<>()).newEmpty());
    }