    @Test
    public void select()
    {
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues(
                "1", "2",
                "2", "1",
                "3", "3");
        Assert.assertEquals(FastList.newListWith("1"), MapIterate.select(map, "1"::equals));
    }