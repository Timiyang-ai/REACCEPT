@Test
    public void testRegister() throws IOException, SQLException
    {
        int assetstore = 0;
        File f = new File(testProps.get("test.bitstream").toString());
        Bitstream registered = Bitstream.register(context,assetstore, f.getName());
        //the item created by default has no name nor type set
        assertThat("testRegister 0", registered.getFormat().getMIMEType(), equalTo("application/octet-stream"));
        assertThat("testRegister 1", registered.getName(), nullValue());
    }