@Test(groups={"tck"})
    public void test_toString_formatter() {
        final OffsetDate date = OffsetDate.of(2010, 12, 3, OFFSET_PONE);
        CalendricalFormatter f = new CalendricalFormatter() {
            @Override
            public String print(DateTimeAccessor accessor) {
                assertEquals(accessor, date);
                return "PRINTED";
            }
            @Override
            public <T> T parse(CharSequence text, Class<T> type) {
                throw new AssertionError();
            }
        };
        String t = date.toString(f);
        assertEquals(t, "PRINTED");
    }