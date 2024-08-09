    @Test
    public void parse() {
        Map<Application.Name, List<List<String>>> body = new HashMap<Application.Name, List<List<String>>>();
        List<List<String>> sub1 = new ArrayList<List<String>>();
        List<String> subSub1 = new ArrayList<String>();

        subSub1.add(null);
        sub1.add(subSub1);
        body.put(TESTAPP, sub1);

        ImpressionsActionsResourceParser.parse(body);

        assertNotNull(subSub1.get(0));
    }