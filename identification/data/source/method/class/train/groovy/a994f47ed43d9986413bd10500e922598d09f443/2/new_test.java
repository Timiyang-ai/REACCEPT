    @Test
    public void cleanUpNullReferences() {
        UnlimitedConcurrentCache<String, Object> sc =
                new UnlimitedConcurrentCache<>(
                        new LinkedHashMap<>(
                                Maps.of("name", "Daniel",
                                        "gender", "Male",
                                        "city", new SoftReference(null))
                        )
                );

        sc.cleanUpNullReferences();
        Assert.assertEquals(new TreeSet<>(Arrays.asList("Daniel", "Male")), new TreeSet<>(sc.values()));
    }