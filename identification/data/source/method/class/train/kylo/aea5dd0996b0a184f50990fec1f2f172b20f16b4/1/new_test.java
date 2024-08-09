@Test
    public void testDropTable() throws Exception {
        // Mock SQL objects
        final Statement statement = Mockito.mock(Statement.class);
        Mockito.when(statement.execute(Mockito.anyString())).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(final InvocationOnMock invocation) throws Throwable {
                final String sql = (String)invocation.getArguments()[0];
                if (sql.equals("DROP TABLE IF EXISTS `invalid`")) {
                    throw new SQLException();
                }
                return true;
            }
        });

        Mockito.when(connection.createStatement()).thenReturn(statement);

        // Test dropping table with success
        final TableRegisterSupport support = new TableRegisterSupport(connection);
        Assert.assertTrue(support.dropTable("`feed`"));
        Mockito.verify(statement).execute("DROP TABLE IF EXISTS `feed`");

        // Test dropping table with exception
        Assert.assertFalse(support.dropTable("`invalid`"));
    }