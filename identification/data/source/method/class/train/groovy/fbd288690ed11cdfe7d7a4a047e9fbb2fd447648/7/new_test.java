    @Test
    public void containsKey() {
        CommonCache<String, String> sc =
                new CommonCache<>(
                        new LinkedHashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", "Shanghai")
                        )
                );

        Assert.assertTrue(sc.containsKey("name"));
    }