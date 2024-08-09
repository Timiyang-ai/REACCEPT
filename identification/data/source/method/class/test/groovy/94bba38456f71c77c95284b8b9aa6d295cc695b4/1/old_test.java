    @Test
    public void get() {
        UnlimitedConcurrentCache<String, String> sc =
                new UnlimitedConcurrentCache<>(
                        new LinkedHashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", "Shanghai")
                        )
                );

        Assert.assertEquals("Daniel", sc.get("name"));
        Assert.assertEquals("Male", sc.get("gender"));
        Assert.assertEquals("Shanghai", sc.get("city"));
        Assert.assertNull(sc.get("foo"));
    }