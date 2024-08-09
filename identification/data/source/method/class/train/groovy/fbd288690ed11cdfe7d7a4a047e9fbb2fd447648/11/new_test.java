    @Test
    public void remove() {
        CommonCache<String, String> sc =
                new CommonCache<>(
                        new HashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", "Shanghai")
                        )
                );

        Assert.assertEquals("Shanghai", sc.remove("city"));
        Assert.assertNull(sc.get("city"));
    }