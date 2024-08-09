    @Test
    public void get() {
        assertEquals(2, FluentIterable.of(iterableEven).get(0).intValue());

        try {
            FluentIterable.of(iterableEven).get(-1);
            fail("expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ioe) {
            // expected
        }

        try {
            FluentIterable.of(iterableEven).get(IterableUtils.size(iterableEven));
            fail("expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ioe) {
            // expected
        }
    }