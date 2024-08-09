@Test
    public void testRegister() throws IOException, SQLException, AuthorizeException
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            authorizeService.authorizeAction((Context) any, (Bitstream) any,
                    Constants.WRITE); result = null;
        }};
        int assetstore = 0;
        File f = new File(testProps.get("test.bitstream").toString());
        Bitstream registered = bitstreamService.register(context,assetstore, f.getName());
        //the item created by default has no name nor type set
        assertThat("testRegister 0", registered.getFormat(context).getMIMEType(), equalTo("application/octet-stream"));
        assertThat("testRegister 1", registered.getName(), nullValue());
    }