    @Test
    public void testdecodeURI() throws Exception {
        assertNull( "Expected null", decodeURI(null) );

        assertEquals( "decoded",
            "#($^&#&(*$C@#$*&^@#*&(|}|}{|}dfaj;",
            decodeURI( "%23(%24%5E%26%23%26(*%24C%40%23%24*%26%5E%40%23*%26(%7C%7D%7C%7D%7B%7C%7Ddfaj%3B" ) );
    }