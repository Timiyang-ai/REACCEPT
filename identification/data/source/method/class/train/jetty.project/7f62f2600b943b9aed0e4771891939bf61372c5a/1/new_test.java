@Test
    public void testDecodePath()
    {
        assertEquals("/foo/bar",URIUtil.decodePath("xx/foo/barxx",2,8));
        assertEquals("/foo/bar",URIUtil.decodePath("/foo/bar"));
        assertEquals("/f o/b r",URIUtil.decodePath("/f%20o/b%20r"));
        assertEquals("/foo/bar",URIUtil.decodePath("/foo;ignore/bar;ignore"));
        assertEquals("/fää/bar",URIUtil.decodePath("/fää;ignore/bar;ignore"));
        assertEquals("/f\u0629\u0629%23/bar",URIUtil.decodePath("/f%d8%a9%d8%a9%2523;ignore/bar;ignore"));
        
        assertEquals("foo%23;,:=b a r",URIUtil.decodePath("foo%2523%3b%2c:%3db%20a%20r;rubbish"));
        assertEquals("/foo/bar%23;,:=b a r=",URIUtil.decodePath("xxx/foo/bar%2523%3b%2c:%3db%20a%20r%3Dxxx;rubbish",3,35));
        assertEquals("fää%23;,:=b a r=",URIUtil.decodePath("fää%2523%3b%2c:%3db%20a%20r%3D"));
        assertEquals("f\u0629\u0629%23;,:=b a r",URIUtil.decodePath("f%d8%a9%d8%a9%2523%3b%2c:%3db%20a%20r"));
        
        // Test for null character (real world ugly test case)
        byte oddBytes[] = { '/', 0x00, '/' };
        String odd = new String(oddBytes, StandardCharsets.ISO_8859_1);
        assertEquals(odd,URIUtil.decodePath("/%00/"));
    }