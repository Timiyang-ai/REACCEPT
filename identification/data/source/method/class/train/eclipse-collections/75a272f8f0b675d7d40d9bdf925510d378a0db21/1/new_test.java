    @Test
    public void assertContainsAllEntries()
    {
        try
        {
            MutableListMultimap<String, Integer> multimap = FastListMultimap.newMultimap(Tuples.pair("one", 1), Tuples.pair("two", 2));
            Verify.assertContainsAllEntries(multimap, "one", 1, "three", 3);
            Assert.fail();
        }
        catch (AssertionError e)
        {
            Verify.assertContains(VerifyTest.class.getName(), e.getStackTrace()[0].toString());
        }
    }