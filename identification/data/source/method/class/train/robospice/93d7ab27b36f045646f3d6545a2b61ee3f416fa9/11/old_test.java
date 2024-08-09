    public void test_execute_should_fail_if_not_started() {
        // given

        // when
        try {
            spiceManager.execute(new CachedSpiceRequest<String>((SpiceRequest<String>) null, null, DurationInMillis.ALWAYS_RETURNED), null);
            // then
            fail();
        } catch (Exception ex) {
            Ln.d(ex);
            // then
            assertTrue(true);
        }
    }