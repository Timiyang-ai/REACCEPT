    @Test
    public void attributeIn()
    {
        MutableList<String> upperList = Lists.fixedSize.of("A", "B");
        Predicate<String> in = Predicates.attributeIn(StringFunctions.toUpperCase(), upperList);
        PredicatesTest.assertAccepts(in, "a");
        PredicatesTest.assertRejects(in, "c");

        Assert.assertEquals(FastList.newListWith("a"), ListIterate.select(Lists.fixedSize.of("a", "c"), in));
        PredicatesTest.assertToString(in);
    }