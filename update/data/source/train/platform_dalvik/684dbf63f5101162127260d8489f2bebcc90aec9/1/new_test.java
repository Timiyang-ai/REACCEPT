@TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "read",
        args = {}
    )         
    public void test_read() throws IOException {
        Support_ASimpleReader ssr = new Support_ASimpleReader(true);
        try {
            br = new BufferedReader(new Support_StringReader(testString));
            int r = br.read();
            assertTrue("Char read improperly", testString.charAt(0) == r);
            br = new BufferedReader(new Support_StringReader(new String(
                    new char[] { '\u8765' })));
            assertTrue("Wrong double byte character", br.read() == '\u8765');
        } catch (java.io.IOException e) {
            fail("Exception during read test");
        }

        char[] chars = new char[256];
        for (int i = 0; i < 256; i++)
            chars[i] = (char) i;
        Reader in = new BufferedReader(new Support_StringReader(new String(
                chars)), 12);
        try {
            assertEquals("Wrong initial char", 0, in.read()); // Fill the
            // buffer
            char[] buf = new char[14];
            in.read(buf, 0, 14); // Read greater than the buffer
            assertTrue("Wrong block read data", new String(buf)
                    .equals(new String(chars, 1, 14)));
            assertEquals("Wrong chars", 15, in.read()); // Check next byte
        } catch (IOException e) {
            fail("Exception during read test 2:" + e);
        }
        
        // regression test for HARMONY-841
        assertTrue(new BufferedReader(new CharArrayReader(new char[5], 1, 0), 2).read() == -1);

        br.close();
        br = new BufferedReader(ssr);
        try {
            br.read();
            fail("IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        // Avoid IOException in tearDown().
        ssr.throwExceptionOnNextUse = false;
    }