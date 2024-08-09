    @Test
    public void clear() {
        UnlimitedConcurrentCache<String, String> sc =
                new UnlimitedConcurrentCache<>(
                        new LinkedHashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", "Shanghai")
                        )
                );

        Assert.assertEquals(new TreeSet<>(Arrays.asList("Daniel", "Male", "Shanghai")), new TreeSet<>(sc.clearAll().values()));
    }