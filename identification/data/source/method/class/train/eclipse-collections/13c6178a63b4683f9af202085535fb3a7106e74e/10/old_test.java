    @Test
    public void collectIf()
    {
        Object[] integers = Lists.fixedSize.of(1, 2, 3).toArray();
        Verify.assertContainsAll(ArrayIterate.collectIf(integers, Integer.class::isInstance, String::valueOf), "1", "2", "3");
        Verify.assertContainsAll(ArrayIterate.collectIf(integers, Integer.class::isInstance, String::valueOf, FastList.newList()), "1", "2", "3");
    }