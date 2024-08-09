    @Test
    void composeToLong() {
        final ToLong<String> toLong = String::length;
        final ComposeToLong<String, String, ToLong<String>> composeToLong =
                (ComposeToLong<String, String, ToLong<String>>)
                        ComposedUtil.composeToLong(Function.identity(), toLong);

        assertNotNull(composeToLong.firstStep());
        assertNotNull(composeToLong.secondStep());

        assertNull(composeToLong.apply(null));
        assertNotNull(composeToLong.apply("test"));
    }