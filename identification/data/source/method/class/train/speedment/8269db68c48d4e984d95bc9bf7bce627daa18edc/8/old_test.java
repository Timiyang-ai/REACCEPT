    @Test
    void composeToBigDecimal() {
        final ToBigDecimal<String> toBigDecimal = string -> BigDecimal.valueOf(string.length());
        final ComposeToBigDecimal<String, String, ToBigDecimal<String>> composeToBigDecimal =
                (ComposeToBigDecimal<String, String, ToBigDecimal<String>>)
                        ComposedUtil.composeToBigDecimal(Function.identity(), toBigDecimal);

        assertNotNull(composeToBigDecimal.firstStep());
        assertNotNull(composeToBigDecimal.secondStep());

        assertNull(composeToBigDecimal.apply(null));
        assertNotNull(composeToBigDecimal.apply("test"));
    }