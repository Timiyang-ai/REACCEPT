    @Test
    public void take()
    {
        MutableList<Integer> integers = this.getIntegerList();
        this.assertTake(integers);
        this.assertTake(new LinkedList<>(integers));

        Verify.assertSize(0, ListIterate.take(Lists.fixedSize.of(), 2));
        Verify.assertSize(0, ListIterate.take(new LinkedList<>(), 2));
        Verify.assertSize(0, ListIterate.take(new LinkedList<>(), Integer.MAX_VALUE));

        Verify.assertThrows(IllegalArgumentException.class, () -> ListIterate.take(this.getIntegerList(), -1));
        Verify.assertThrows(IllegalArgumentException.class, () -> ListIterate.take(this.getIntegerList(), -1, FastList.newList()));
    }