@Test
    public void testDelete() throws IOException, SQLException
    {      
        // Create a new bitstream, which we can delete. As ordering of these 
        // tests is unpredictable we don't want to delete the global bitstream
        File f = new File(testProps.get("test.bitstream").toString());
        Bitstream delBS = Bitstream.create(context, new FileInputStream(f));
        
        assertFalse("testIsDeleted 0", delBS.isDeleted());
        delBS.delete();
        assertTrue("testDelete 0", delBS.isDeleted());
    }