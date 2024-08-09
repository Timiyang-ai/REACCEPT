@Test
    public void testGetBuffer ()
    {
        System.out.println("getBuffer");

        RunTable instance = createVerticalInstance();
        String expResult = "ip[width=10, height=5, bits=8, min=0.0, max=255.0]";
        ByteProcessor result = instance.getBuffer();
        System.out.println("result: " + result);
        assertEquals(expResult, result.toString());
    }