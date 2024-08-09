    @DataProvider(name = "flatten")
    public Iterator<Object[]> flattenTestCases() {
        List<Object[]> data;

        data = new ArrayList<Object[]>();

        data.add(new Object[] {"a b c", new String[] {"a", "b", "c"}, " "});
        data.add(new Object[] {"a*b*c", new String[] {"a", "b", "c"}, "*"});
        data.add(new Object[] {"a*c", new String[] {"a", "", "c"}, "*"});
        data.add(new Object[] {"a*c", new String[] {"a", null, "c"}, "*"});
        data.add(new Object[] {"b", new String[] {null, "b", null}, "*"});
        data.add(new Object[] {"", new String[] {null, null, null}, "*"});

        return data.iterator();
    }