@Test
    public void testDelete() throws IOException, SQLException, AuthorizeException
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow Bitstream WRITE perms
                authorizeService.authorizeAction((Context) any, (Bitstream) any,
                    Constants.WRITE); result = null;
                authorizeService.authorizeAction((Context) any, (Bitstream) any,
                    Constants.DELETE); result = null;

        }};
        // Create a new bitstream, which we can delete. As ordering of these
        // tests is unpredictable we don't want to delete the global bitstream
        File f = new File(testProps.get("test.bitstream").toString());
        Bitstream delBS = bitstreamService.create(context, new FileInputStream(f));
        
        assertFalse("testIsDeleted 0", delBS.isDeleted());
        bitstreamService.delete(context, delBS);
        assertTrue("testDelete 0", delBS.isDeleted());
    }