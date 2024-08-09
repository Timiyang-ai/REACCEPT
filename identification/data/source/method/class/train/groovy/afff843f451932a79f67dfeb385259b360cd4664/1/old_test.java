    @Test
    public void getAndPut() {
        UnlimitedConcurrentCache<String, String> sc = new UnlimitedConcurrentCache<>();

        EvictableCache.ValueProvider vp =
                (EvictableCache.ValueProvider<String, String>) key -> "Chinese";

        Assert.assertEquals("Chinese", sc.getAndPut("language", vp));
        Assert.assertEquals("Chinese", sc.get("language"));
    }