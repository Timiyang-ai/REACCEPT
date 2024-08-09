    @Test
    public void attributeNotIn()
    {
        MutableList<String> lowerList = Lists.fixedSize.of("a", "b");
        Predicate<String> out = Predicates.attributeNotIn(StringFunctions.toLowerCase(), lowerList);
        PredicatesTest.assertAccepts(out, "C");
        PredicatesTest.assertRejects(out, "A");

        Assert.assertEquals(FastList.newListWith("A"), ListIterate.reject(Lists.fixedSize.of("A", "C"), out));
        PredicatesTest.assertToString(out);
    }