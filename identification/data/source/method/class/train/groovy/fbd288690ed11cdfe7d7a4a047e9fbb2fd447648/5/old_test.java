    @Test
    public void get() {
        CommonCache<String, String> sc =
                new CommonCache<>(
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