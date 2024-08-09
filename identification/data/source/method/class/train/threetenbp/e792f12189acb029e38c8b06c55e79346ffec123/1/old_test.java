@Test(groups={"tck"})
    public void test_toString_formatter() {
        final LocalDate date = LocalDate.of(2010, 12, 3);
        CalendricalFormatter f = new CalendricalFormatter() {
            @Override
            public String print(DateTimeAccessor calendrical) {
                assertEquals(calendrical, date);
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