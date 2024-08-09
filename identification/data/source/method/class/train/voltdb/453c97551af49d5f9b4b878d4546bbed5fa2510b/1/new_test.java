@Test
    public void testFirst() throws Exception {

        assertEquals(hsql3RowRS.absolute(2), volt3RowRS.absolute(2));
        assertTrue(hsql3RowRS.first() == volt3RowRS.first());
        assertTrue(hsql3RowRS.getRow() == volt3RowRS.getRow());
        assertEquals(hsql3RowRS.getInt(1), volt3RowRS.getInt(1));
    }