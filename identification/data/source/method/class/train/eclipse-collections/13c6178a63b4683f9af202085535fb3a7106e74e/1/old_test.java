    @Test
    public void drop()
    {
        MutableList<Integer> integers = this.getIntegerList();
        this.assertDrop(integers);
        this.assertDrop(new LinkedList<>(integers));

        Verify.assertSize(0, ListIterate.drop(Lists.fixedSize.<Integer>of(), 2));
        Verify.assertSize(0, ListIterate.drop(new LinkedList<>(), 2));
        Verify.assertSize(0, ListIterate.drop(new LinkedList<>(), Integer.MAX_VALUE));

        Verify.assertThrows(IllegalArgumentException.class, () -> ListIterate.drop(FastList.newList(), -1));
        Verify.assertThrows(IllegalArgumentException.class, () -> ListIterate.drop(FastList.newList(), -1, FastList.newList()));
    }