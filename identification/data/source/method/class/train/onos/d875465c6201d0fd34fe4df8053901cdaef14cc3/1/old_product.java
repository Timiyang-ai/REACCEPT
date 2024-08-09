public static List<LionBundle> generateBundles(String base,
                                                   String... tags) {
        List<LionBundle> result = new ArrayList<>(tags.length);
        BundleStitcher stitcher = new BundleStitcher(base);
        for (String tag : tags) {
            try {
                LionBundle b = stitcher.stitch(tag);
                result.add(b);

            } catch (IllegalArgumentException e) {
                log.warn("Unable to generate bundle: {} / {}", base, tag);
            }
        }
        return ImmutableList.copyOf(result);
    }