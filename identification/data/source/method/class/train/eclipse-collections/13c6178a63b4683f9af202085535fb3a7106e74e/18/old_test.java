    @Test
    public void rejectWith()
    {
        MutableList<Integer> list = this.getIntegerList();
        Verify.assertEmpty(ListIterate.rejectWith(list, Predicates2.instanceOf(), Integer.class));
        Verify.assertEmpty(ListIterate.rejectWith(new LinkedList<>(list), Predicates2.instanceOf(), Integer.class));
    }