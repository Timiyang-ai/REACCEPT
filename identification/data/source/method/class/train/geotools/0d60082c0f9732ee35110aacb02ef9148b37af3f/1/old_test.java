@Test
    public void equals() throws CQLException {
        Filter resultFilter;

        // EQUALS
        resultFilter = CompilerUtil.parseFilter(language, "EQUALS(ATTR1, POINT(1 2))");

        Assert.assertTrue("not an instance of Equals", resultFilter instanceof Equals);
    }