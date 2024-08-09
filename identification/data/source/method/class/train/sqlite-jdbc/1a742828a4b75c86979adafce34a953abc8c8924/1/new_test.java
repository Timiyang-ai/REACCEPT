    @Test
    public void batch() throws SQLException {
        ResultSet rs;

        stat.executeUpdate("create table test (c1, c2, c3, c4);");
        PreparedStatement prep = conn.prepareStatement("insert into test values (?,?,?,?);");
        for (int i = 0; i < 10; i++) {
            prep.setInt(1, Integer.MIN_VALUE + i);
            prep.setFloat(2, Float.MIN_VALUE + i);
            prep.setString(3, "Hello " + i);
            prep.setDouble(4, Double.MAX_VALUE + i);
            prep.addBatch();
        }
        assertArrayEq(prep.executeBatch(), new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
        prep.close();

        rs = stat.executeQuery("select * from test;");
        for (int i = 0; i < 10; i++) {
            assertTrue(rs.next());
            assertEquals(rs.getInt(1), Integer.MIN_VALUE + i);
            assertEquals(rs.getFloat(2), Float.MIN_VALUE + i, 0.0001);
            assertEquals(rs.getString(3), "Hello " + i);
            assertEquals(rs.getDouble(4), Double.MAX_VALUE + i, 0.0001);
        }
        rs.close();
        stat.executeUpdate("drop table test;");
    }