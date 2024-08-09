    @Test
    public void convertToDelimitedStream() throws Exception {
        // Mock result set metadata
        final ResultSetMetaData metadata = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(metadata.getColumnCount()).thenReturn(6);
        Mockito.when(metadata.getColumnName(1)).thenReturn("event");
        Mockito.when(metadata.getColumnName(2)).thenReturn("empty");
        Mockito.when(metadata.getColumnName(3)).thenReturn("date");
        Mockito.when(metadata.getColumnName(4)).thenReturn("time");
        Mockito.when(metadata.getColumnName(5)).thenReturn("timestamp");
        Mockito.when(metadata.getColumnName(6)).thenReturn("custom");
        Mockito.when(metadata.getColumnType(1)).thenReturn(Types.VARCHAR);
        Mockito.when(metadata.getColumnType(2)).thenReturn(Types.VARCHAR);
        Mockito.when(metadata.getColumnType(3)).thenReturn(Types.DATE);
        Mockito.when(metadata.getColumnType(4)).thenReturn(Types.TIME);
        Mockito.when(metadata.getColumnType(5)).thenReturn(Types.TIMESTAMP);
        Mockito.when(metadata.getColumnType(6)).thenReturn(Types.TIMESTAMP);

        // Mock result set
        final ResultSet results = Mockito.mock(ResultSet.class);
        Mockito.when(results.getDate(3)).thenReturn(new Date(1483660800000L));
        Mockito.when(results.getDate(6)).thenThrow(SQLException.class);
        Mockito.when(results.getMetaData()).thenReturn(metadata);
        Mockito.when(results.getString(1)).thenReturn("Fun Friday");
        Mockito.when(results.getTime(4)).thenReturn(new Time(42600000L));
        Mockito.when(results.getTimestamp(3)).thenThrow(SQLException.class);
        Mockito.when(results.getTimestamp(5)).thenReturn(new Timestamp(1483703400000L));
        Mockito.when(results.getTimestamp(6)).thenThrow(SQLException.class);
        Mockito.when(results.next()).thenReturn(true).thenReturn(false);

        // Test converting to delimited text
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final RowVisitor visitor = Mockito.mock(RowVisitor.class);
        JdbcCommon.convertToDelimitedStream(results, out, visitor, " ");

        final InOrder inOrder = Mockito.inOrder(visitor);
        inOrder.verify(visitor).visitRow(results);
        inOrder.verify(visitor).visitColumn("event", Types.VARCHAR, "Fun Friday");
        inOrder.verify(visitor).visitColumn("empty", Types.VARCHAR, (String) null);
        inOrder.verify(visitor).visitColumn("date", Types.DATE, new Timestamp(1483660800000L));
        inOrder.verify(visitor).visitColumn("time", Types.TIME, new Time(42600000L));
        inOrder.verify(visitor).visitColumn("timestamp", Types.TIMESTAMP, new Timestamp(1483703400000L));
        inOrder.verify(visitor).visitColumn("custom", Types.TIMESTAMP, (Timestamp) null);
        inOrder.verifyNoMoreInteractions();

        assertEquals("event empty date time timestamp custom\n\"Fun Friday\"  2017-01-06T00:00:00.000Z 11:50:00.000Z 2017-01-06T11:50:00.000Z \n", new String(out.toByteArray(), "UTF-8"));
    }