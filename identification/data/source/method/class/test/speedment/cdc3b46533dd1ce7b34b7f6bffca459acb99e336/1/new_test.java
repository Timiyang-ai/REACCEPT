    @Test
    void composeToBooleanNullable() {
        final ToBooleanNullable<String> toBooleanNullable = string -> string.length() > 2;
        final ComposeToBooleanNullable<String, String, ToBooleanNullable<String>> composeToBooleanNullable =
                (ComposeToBooleanNullable<String, String, ToBooleanNullable<String>>)
                        ComposedUtil.composeToBooleanNullable(Function.identity(), toBooleanNullable);

        assertNotNull(composeToBooleanNullable.firstStep());
        assertNotNull(composeToBooleanNullable.secondStep());

        assertTrue(composeToBooleanNullable.applyAsBoolean("test"));

        assertNull(composeToBooleanNullable.apply(null));
        assertNotNull(composeToBooleanNullable.apply("test"));
    }