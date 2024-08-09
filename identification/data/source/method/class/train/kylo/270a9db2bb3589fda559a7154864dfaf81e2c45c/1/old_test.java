    @Test
    public void fromResultSet() throws Exception {
        final ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getString(JdbcConstants.TABLE_CAT)).thenReturn("finance");
        Mockito.when(rs.getString(JdbcConstants.TABLE_SCHEM)).thenReturn("dbo");
        Mockito.when(rs.getString(JdbcConstants.TABLE_NAME)).thenReturn("people");
        Mockito.when(rs.getString(JdbcConstants.TABLE_TYPE)).thenReturn("TABLE");
        Mockito.when(rs.getString(JdbcConstants.REMARKS)).thenReturn("Finance People");

        final DefaultJdbcTable table = DefaultJdbcTable.fromResultSet(rs, Mockito.mock(DatabaseMetaData.class));
        Assert.assertEquals("finance", table.getCatalog());
        Assert.assertEquals("dbo", table.getSchema());
        Assert.assertEquals("people", table.getName());
        Assert.assertEquals("finance.dbo.people", table.getQualifiedIdentifier());
        Assert.assertEquals("TABLE", table.getType());
        Assert.assertEquals("Finance People", table.getRemarks());

        final DatabaseMetaData metaData = Mockito.mock(DatabaseMetaData.class);
        Mockito.when(metaData.getCatalogSeparator()).thenReturn(".");
        Mockito.when(metaData.getIdentifierQuoteString()).thenReturn("\"");

        table.setMetaData(metaData);
        Assert.assertEquals("\"finance\".\"dbo\".\"people\"", table.getQualifiedIdentifier());
    }