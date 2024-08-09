@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "IOException checking missed.",
        method = "read",
        args = {}
    )    
    
    public void testRead1() throws IOException {
        // if the decoder is constructed by InputStreamReader itself, the decoder's 
        // default error action is REPLACE
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(
                new byte[] { -32, -96 }), "UTF-8");
        assertEquals("read() return incorrect value", 65533, isr.read());
        
        InputStreamReader isr2 = new InputStreamReader(new ByteArrayInputStream(
                new byte[] { -32, -96 }), Charset.forName("UTF-8"));
        assertEquals("read() return incorrect value", 65533, isr2.read());
        
        // if the decoder is passed in, keep its status intacted
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        InputStreamReader isr3 = new InputStreamReader(new ByteArrayInputStream(
                new byte[] { -32, -96 }), decoder);
        try{
           isr3.read();
           fail("Should throw MalformedInputException");
        }catch(MalformedInputException e){
            //expected
        }
        
        CharsetDecoder decoder2 = Charset.forName("UTF-8").newDecoder();
        decoder2.onMalformedInput(CodingErrorAction.IGNORE);
        InputStreamReader isr4 = new InputStreamReader(new ByteArrayInputStream(
                new byte[] { -32, -96 }), decoder2);
        assertEquals("read() return incorrect value", -1, isr4.read());
        
        CharsetDecoder decoder3 = Charset.forName("UTF-8").newDecoder();
        decoder3.onMalformedInput(CodingErrorAction.REPLACE);
        InputStreamReader isr5 = new InputStreamReader(new ByteArrayInputStream(
                new byte[] { -32, -96 }), decoder3);
        assertEquals("read() return incorrect value", 65533, isr5.read());
    }