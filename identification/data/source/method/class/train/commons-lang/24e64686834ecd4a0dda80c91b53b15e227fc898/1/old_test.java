@Test
    public void testDefaultIfNull() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(dflt, ObjectUtils.defaultIfNull(null, dflt), "dflt was not returned when o was null");
        assertSame(o, ObjectUtils.defaultIfNull(o, dflt), "dflt was returned when o was not null");
        assertSame(dflt, ObjectUtils.defaultIfNull(null, () -> dflt), "dflt was not returned when o was null");
        assertSame(o, ObjectUtils.defaultIfNull(o, () -> dflt), "dflt was returned when o was not null");
        assertSame(o, ObjectUtils.defaultIfNull(FOO, () -> dflt), "dflt was returned when o was not null");
        assertSame(o, ObjectUtils.defaultIfNull("foo", () -> dflt), "dflt was returned when o was not null");
        MutableInt callsCounter = new MutableInt(0);
        Supplier<Object> countingDefaultSupplier = () -> {
            callsCounter.increment();
            return dflt;
        };
        ObjectUtils.defaultIfNull(o, countingDefaultSupplier);
        assertEquals(0, callsCounter.getValue());
        ObjectUtils.defaultIfNull(null, countingDefaultSupplier);
        assertEquals(1, callsCounter.getValue());
    }