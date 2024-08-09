    @Test
    public void openConnectionSessionTest() throws IOException, InterruptedException {
        Connection conn = Mockito.mock(Connection.class);
        PowerMockito.mockStatic(Thread.class);
        SshHelper.openConnectionSession(conn);

        Mockito.verify(conn).openSession();

        PowerMockito.verifyStatic();

    }