    @DataProvider(name = "parseIntDef")
    public Iterator<Object[]> parseIntDefTestCases() {
      List<Object[]> data;

        data = new ArrayList<Object[]>();

        for(int i = 0; i < 10; i++) {
            data.add(new Object[] {Integer.toString(i), 0, i});
            data.add(new Object[] {"foobar", i, i});
        }

        return data.iterator();
    }