@Test
    public void testDecodePath()
    {
        assertEquals("foo%23;,:=b a r",URIUtil.decodePath("foo%2523%3b%2c:%3db%20a%20r;rubbish"));
        assertEquals("foo%23;,:=b a r=",URIUtil.decodePath("xxxfoo%2523%3b%2c:%3db%20a%20r%3Dxxx;rubbish".getBytes(),3,30));
        assertEquals("fää%23;,:=b a r=",URIUtil.decodePath("fää%2523%3b%2c:%3db%20a%20r%3D"));
        assertEquals("f\u0629\u0629%23;,:=b a r",URIUtil.decodePath("f%d8%a9%d8%a9%2523%3b%2c:%3db%20a%20r"));
        
        // Test for null character (real world ugly test case)
        byte oddBytes[] = { '/', 0x00, '/' };
        String odd = new String(oddBytes, StandardCharsets.ISO_8859_1);
        assertEquals(odd,URIUtil.decodePath("/%00/"));
    }