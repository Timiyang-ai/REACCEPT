    @Test
    public void collect()
    {
        MutableList<Boolean> list = Lists.fixedSize.of(true, false, null);
        List<Boolean> linked = new LinkedList<>(list);
        this.assertCollect(list);
        this.assertCollect(list.asSynchronized());
        this.assertCollect(list.asUnmodifiable());
        this.assertCollect(linked);
    }