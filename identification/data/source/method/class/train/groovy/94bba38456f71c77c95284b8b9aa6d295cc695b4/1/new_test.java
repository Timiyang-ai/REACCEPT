    @Test
    public void put() {
        UnlimitedConcurrentCache<String, String> sc = new UnlimitedConcurrentCache<>();

        Assert.assertNull(sc.put("name", "Daniel"));
        Assert.assertEquals("Daniel", sc.get("name"));

        Assert.assertEquals("Daniel", sc.put("name", "sunlan"));
        Assert.assertEquals("sunlan", sc.get("name"));
    }