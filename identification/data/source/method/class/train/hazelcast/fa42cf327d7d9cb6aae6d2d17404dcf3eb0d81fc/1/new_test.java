    @Before
    public void setup() {
        IMap<String, String> stringMap = getMapFromSteadyMember();
        stringMap.put("1", "1");
    }