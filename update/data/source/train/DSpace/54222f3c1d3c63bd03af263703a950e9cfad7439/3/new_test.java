@Test
    public void testGetType()
            throws SQLException
    {
        System.out.println("getType");
        int expResult = Constants.EPERSON;
        int result = eperson.getType();
        assertEquals("Should return Constants.EPERSON", expResult, result);
    }