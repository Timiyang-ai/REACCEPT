    @DataProvider(name = "capitalize")
    public Iterator<String[]> capitalizeTestCases() {
        List<String[]> data;

        data = new ArrayList<String[]>();

        data.add(new String[] {"bob",         "Bob"});
        data.add(new String[] {"BOB",         "Bob"});
        data.add(new String[] {"bOB",         "Bob"});
        data.add(new String[] {"boB",         "Bob"});
        data.add(new String[] {"Bob",         "Bob"});
        data.add(new String[] {"Bob Servant", "Bob servant"});
        data.add(new String[] {"b",           "B"});
        data.add(new String[] {"",            ""});
        data.add(new String[] {null,          ""});
        data.add(new String[] {"7",           "7"});

        return data.iterator();
    }