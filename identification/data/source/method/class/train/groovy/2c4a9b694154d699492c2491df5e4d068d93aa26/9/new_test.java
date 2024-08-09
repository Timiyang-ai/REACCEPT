    @Test
    public void cleanUpNullReferences() {
        ConcurrentCommonCache<String, String> sc =
                new ConcurrentCommonCache<>(
                        new LinkedHashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", null)
                        )
                );

        sc.cleanUpNullReferences();
        Assert.assertArrayEquals(new String[] {"Daniel", "Male"}, sc.values().toArray(new String[0]));
    }