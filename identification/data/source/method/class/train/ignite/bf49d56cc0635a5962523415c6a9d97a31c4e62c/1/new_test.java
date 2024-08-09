@Test
    public void testGetTables() throws Exception {
        try (Connection conn = DriverManager.getConnection(URL)) {
            DatabaseMetaData meta = conn.getMetaData();

            ResultSet rs = meta.getTables(null, "pers", "%", new String[]{"TABLE"});
            assertNotNull(rs);
            assertTrue(rs.next());
            assertEquals("TABLE", rs.getString("TABLE_TYPE"));
            assertEquals(JdbcUtils.CATALOG_NAME, rs.getString("TABLE_CAT"));
            assertEquals("PERSON", rs.getString("TABLE_NAME"));

            assertFalse(rs.next());

            rs = meta.getTables(null, "org", "%", new String[]{"TABLE"});
            assertNotNull(rs);
            assertTrue(rs.next());
            assertEquals("TABLE", rs.getString("TABLE_TYPE"));
            assertEquals(JdbcUtils.CATALOG_NAME, rs.getString("TABLE_CAT"));
            assertEquals("ORGANIZATION", rs.getString("TABLE_NAME"));

            assertFalse(rs.next());

            rs = meta.getTables(null, "pers", "%", null);
            assertNotNull(rs);
            assertTrue(rs.next());
            assertEquals("TABLE", rs.getString("TABLE_TYPE"));
            assertEquals(JdbcUtils.CATALOG_NAME, rs.getString("TABLE_CAT"));
            assertEquals("PERSON", rs.getString("TABLE_NAME"));

            assertFalse(rs.next());

            rs = meta.getTables(null, "org", "%", null);
            assertNotNull(rs);
            assertTrue(rs.next());
            assertEquals("TABLE", rs.getString("TABLE_TYPE"));
            assertEquals(JdbcUtils.CATALOG_NAME, rs.getString("TABLE_CAT"));
            assertEquals("ORGANIZATION", rs.getString("TABLE_NAME"));

            assertFalse(rs.next());

            rs = meta.getTables(null, "PUBLIC", "", new String[]{"WRONG"});
            assertFalse(rs.next());
        }
    }