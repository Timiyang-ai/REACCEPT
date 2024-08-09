@Test
    public void testGetColumnPrivileges() throws SQLServerException, SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rsTables = databaseMetaData.getTables(null, null, "%", types);
        
        //Fetch one table
        assertTrue(rsTables.next(), "At least one table should be found");
        
        //Go through all columns.
        ResultSet rs1 = databaseMetaData.getColumnPrivileges(null, null, rsTables.getString("TABLE_NAME"), "%");
        
        while(rs1.next()) {
            assertTrue(!StringUtils.isEmpty(rs1.getString("TABLE_CAT")),"Category Name should not be Empty"); //1
            assertTrue(!StringUtils.isEmpty(rs1.getString("TABLE_SCHEM")),"SCHEMA Name should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("TABLE_NAME")),"Table Name should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("COLUMN_NAME")),"COLUMN NAME should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("GRANTOR")),"GRANTOR should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("GRANTEE")),"GRANTEE should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("PRIVILEGE")),"PRIVILEGE should not be Empty");
            assertTrue(!StringUtils.isEmpty(rs1.getString("IS_GRANTABLE")),"IS_GRANTABLE should be YES / NO");

        }
    }