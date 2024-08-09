    @Test
    public void generateBundles() {
        title("generateBundles");
        List<LionBundle> bundles =
                BundleStitcher.generateBundles(LION_BASE, LION_TAGS);
        print(bundles);
        assertEquals("missing the bundle", 1, bundles.size());

        LionBundle b = bundles.get(0);
        assertEquals("wrong id", "CardGame1", b.id());
        assertEquals("unexpected item count", 12, b.size());
        assertEquals("missing 3oak", "Three of a Kind", b.getValue("three_oak"));
        assertEquals("missing queen", "Queen", b.getValue("queen"));
        assertEquals("missing clubs", "Clubs", b.getValue("clubs"));
    }