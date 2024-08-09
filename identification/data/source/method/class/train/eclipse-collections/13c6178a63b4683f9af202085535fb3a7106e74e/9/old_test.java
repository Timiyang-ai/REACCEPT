    @Test
    public void selectWith()
    {
        MutableList<Integer> list = this.getIntegerList();
        Verify.assertSize(5, RandomAccessListIterate.selectWith(list, Predicates2.instanceOf(), Integer.class));
    }