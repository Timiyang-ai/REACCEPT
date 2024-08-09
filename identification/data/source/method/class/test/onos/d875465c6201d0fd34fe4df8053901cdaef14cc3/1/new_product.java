public static List<LionBundle> generateBundles(String base, String... tags) {
        List<LionBundle> bundles = new ArrayList<>(tags.length);
        BundleStitcher stitcher = new BundleStitcher(base);
        for (String tag : tags) {
            try {
                LionBundle lion = stitcher.stitch(tag);
                bundles.add(lion);
                log.info("Generated LION bundle: {}", lion);
                log.debug(" Dumped: {}", lion.dump());

            } catch (IllegalArgumentException e) {
                log.warn("Unable to generate bundle: {} / {}", base, tag);
                log.debug("BOOM!", e);
            }
        }
        return ImmutableList.copyOf(bundles);
    }