@Test
    public void testRevertForbid() {
        String service = "dubbo.test.api.HelloService";
        List<String> forbid = new ArrayList<String>();
        forbid.add(service);
        Set<String> subscribed = new HashSet<String>();
        subscribed.add("perf/+" + service + ":1.0.0");
        List<String> newForbid = UrlUtils.revertForbid(forbid, subscribed);
        List<String> expectForbid = new ArrayList<String>();
        expectForbid.add("perf/+" + service + ":1.0.0");
        assertEquals(expectForbid, newForbid);
    }