    @Test
    public void collectIf()
    {
        MutableList<Integer> integers = Lists.fixedSize.of(1, 2, 3);
        Verify.assertContainsAll(RandomAccessListIterate.collectIf(integers, Integer.class::isInstance, String::valueOf), "1", "2", "3");
        Verify.assertContainsAll(RandomAccessListIterate.collectIf(integers, Integer.class::isInstance, String::valueOf, new ArrayList<>()), "1", "2", "3");
    }