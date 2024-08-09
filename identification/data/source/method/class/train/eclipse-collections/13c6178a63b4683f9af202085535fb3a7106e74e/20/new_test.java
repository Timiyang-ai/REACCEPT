    @Test
    public void collectIf()
    {
        MutableList<Integer> integers = Lists.fixedSize.of(1, 2, 3);
        this.assertCollectIf(integers);
        this.assertCollectIf(new LinkedList<>(integers));
    }