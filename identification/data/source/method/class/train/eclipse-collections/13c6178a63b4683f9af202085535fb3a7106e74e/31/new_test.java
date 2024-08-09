    @Test
    public void selectWith()
    {
        MutableList<Integer> list = this.getIntegerList();
        Verify.assertSize(5, ListIterate.selectWith(list, Predicates2.instanceOf(), Integer.class));
        Verify.assertSize(
                5,
                ListIterate.selectWith(new LinkedList<>(list), Predicates2.instanceOf(), Integer.class));
    }