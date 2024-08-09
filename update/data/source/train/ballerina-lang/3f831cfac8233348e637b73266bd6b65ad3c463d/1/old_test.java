@Test(description = "Remove an element in a valid jsonpath")
    public void testRemove() {
        BValue[] args = {};
        BValue[] returns = BRunUtil.invoke(compileResult, "remove", args);

        final String expected = "{\"state\":\"CA\",\"age\":20}";
        Assert.assertEquals(getJsonAsString(returns[0]), expected);
    }