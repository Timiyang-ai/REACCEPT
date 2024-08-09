@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "An issue in the API code has been identified (ticket #87). This test must be updated when the ticket is closed.",
        method = "close",
        args = {}
    )
    public void test_close() {
        
        fos.setThrowsException(true);
        try {
            osw.close();
            fail("Test 1: IOException expected.");
        } catch (IOException e) {
            // Expected.
        }
        
/* Test 2 does not work and has therefore been disabled (see Ticket #87).        
        // Test 2: Write should not fail since the closing
        // in test 1 has not been successful.
        try {
            osw.write("Lorem ipsum...");
        } catch (IOException e) {
            fail("Test 2: Unexpected IOException.");
        }
        
        // Test 3: Close should succeed.
        fos.setThrowsException(false);
        try {
            osw.close();
        } catch (IOException e) {
            fail("Test 3: Unexpected IOException.");
        }
*/ 
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(bout,
                    "ISO2022JP");
            writer.write(new char[] { 'a' });
            writer.close();
            // The default is ASCII, there should not be any mode changes.
            String converted = new String(bout.toByteArray(), "ISO8859_1");
            assertTrue("Test 4: Invalid conversion: " + converted, 
                       converted.equals("a"));

            bout.reset();
            writer = new OutputStreamWriter(bout, "ISO2022JP");
            writer.write(new char[] { '\u3048' });
            writer.flush();
            // The byte sequence should not switch to ASCII mode until the
            // stream is closed.
            converted = new String(bout.toByteArray(), "ISO8859_1");
            assertTrue("Test 5: Invalid conversion: " + converted, 
                       converted.equals("\u001b$B$("));
            writer.close();
            converted = new String(bout.toByteArray(), "ISO8859_1");
            assertTrue("Test 6: Invalid conversion: " + converted, 
                       converted.equals("\u001b$B$(\u001b(B"));

            bout.reset();
            writer = new OutputStreamWriter(bout, "ISO2022JP");
            writer.write(new char[] { '\u3048' });
            writer.write(new char[] { '\u3048' });
            writer.close();
            // There should not be a mode switch between writes.
            assertEquals("Test 7: Invalid conversion. ", 
                         "\u001b$B$($(\u001b(B", 
                         new String(bout.toByteArray(), "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            // Can't test missing converter.
            System.out.println(e);
        } catch (IOException e) {
            fail("Unexpected: " + e);
        }
    }