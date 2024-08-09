    @Test
    public void getAndPut() {
        CommonCache<String, String> sc = new CommonCache<>();

        EvictableCache.ValueProvider vp =
                (EvictableCache.ValueProvider<String, String>) key -> "Chinese";

        Assert.assertEquals("Chinese", sc.getAndPut("language", vp,false));
        Assert.assertNull(sc.get("language"));

        Assert.assertEquals("Chinese", sc.getAndPut("language", vp));
        Assert.assertEquals("Chinese", sc.get("language"));
    }