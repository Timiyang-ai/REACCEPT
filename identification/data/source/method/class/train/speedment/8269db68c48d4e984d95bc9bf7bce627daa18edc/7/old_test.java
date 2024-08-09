    @Test
    void composeToByte() {
        final ToByte<String> toByte = string -> string.getBytes()[0];
        final ComposeToByte<String, String, ToByte<String>> composeToByte =
                (ComposeToByte<String, String, ToByte<String>>)
                        ComposedUtil.composeToByte(Function.identity(), toByte);

        assertNotNull(composeToByte.firstStep());
        assertNotNull(composeToByte.secondStep());

        assertNull(composeToByte.apply(null));
        assertNotNull(composeToByte.apply("test"));
    }