@Test
    public void testFirst() throws Exception {

        assertTrue(hsql3RowRS.first() == volt3RowRS.first());
        assertTrue(hsql3RowRS.getRow() == volt3RowRS.getRow());
        assertEquals(hsql3RowRS.getInt(1), volt3RowRS.getInt(1));
    }