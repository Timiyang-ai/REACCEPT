    @Test
    public void remove() {
        ConcurrentCommonCache<String, String> sc =
                new ConcurrentCommonCache<>(
                        new HashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", "Shanghai")
                        )
                );

        Assert.assertEquals("Shanghai", sc.remove("city"));
        Assert.assertNull(sc.get("city"));
    }