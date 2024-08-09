    @Test
    public void put() {
        ConcurrentCommonCache<String, String> sc = new ConcurrentCommonCache<>();

        Assert.assertNull(sc.put("name", "Daniel"));
        Assert.assertEquals("Daniel", sc.get("name"));

        Assert.assertEquals("Daniel", sc.put("name", "sunlan"));
        Assert.assertEquals("sunlan", sc.get("name"));
    }