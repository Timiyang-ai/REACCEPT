@SuppressWarnings("ThrowableNotThrown")
    @Test
    public void testClose() throws Exception {
        final Connection conn;

        try (Connection conn0 = DriverManager.getConnection(urlWithAffinityAwarenessProp)) {
            conn = conn0;

            assert conn != null;
            assert !conn.isClosed();
        }

        assert conn.isClosed();

        assert !conn.isValid(2) : "Connection must be closed";

        GridTestUtils.assertThrows(log, new Callable<Object>() {
            @Override public Object call() throws Exception {
                conn.isValid(-2);

                return null;
            }
        }, SQLException.class, "Invalid timeout");
    }