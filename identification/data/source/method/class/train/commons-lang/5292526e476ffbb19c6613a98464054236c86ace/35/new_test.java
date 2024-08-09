@Test
    public void testGetThrowableList_Throwable_null() {
        final List<?> throwables = ExceptionUtils.getThrowableList(null);
        assertEquals(0, throwables.size());
    }