@Test
    public void testVersion() throws IOException, InterruptedException {

        try (ChronicleMap<Integer, Double> expected = ChronicleMapBuilder.of(Integer.class, Double
                .class)
                .create()) {
            expected.put(1, 1.0);

            String version = ((VanillaChronicleMap) expected).version();
            Assert.assertNotNull(BuildVersion.readVersion(), version);

        }
    }