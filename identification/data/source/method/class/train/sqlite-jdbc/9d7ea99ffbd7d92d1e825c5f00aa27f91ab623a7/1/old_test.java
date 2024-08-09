    @Test
    public void clearParameters() throws SQLException {
        stat.executeUpdate("create table tbl (colid integer primary key AUTOINCREMENT, col varchar)");
        stat.executeUpdate("insert into tbl(col) values (\"foo\")");

        PreparedStatement prep = conn.prepareStatement("select colid from tbl where col = ?");

        prep.setString(1, "foo");

        ResultSet rs = prep.executeQuery();
        prep.clearParameters();
        rs.next();

        assertEquals(1, rs.getInt(1));

        rs.close();

        try {
            prep.execute();
            fail("Returned result when values not bound to prepared statement");
        } catch (Exception e) {
            assertEquals("Values not bound to statement", e.getMessage());
        }

        try {
            rs = prep.executeQuery();
            fail("Returned result when values not bound to prepared statement");
        } catch (Exception e) {
            assertEquals("Values not bound to statement", e.getMessage());
        }

        prep.close();

        try {
            prep = conn.prepareStatement("insert into tbl(col) values (?)");
            prep.clearParameters();
            prep.executeUpdate();
            fail("Returned result when values not bound to prepared statement");
        } catch (Exception e) {
            assertEquals("Values not bound to statement", e.getMessage());
        }
    }