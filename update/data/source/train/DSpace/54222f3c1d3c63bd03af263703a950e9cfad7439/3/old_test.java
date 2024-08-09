@Test
    public void testGetType()
            throws SQLException
    {
        System.out.println("getType");
        EPerson instance = new EPerson(context, row1);
        int expResult = Constants.EPERSON;
        int result = instance.getType();
        assertEquals("Should return Constants.EPERSON", expResult, result);
    }