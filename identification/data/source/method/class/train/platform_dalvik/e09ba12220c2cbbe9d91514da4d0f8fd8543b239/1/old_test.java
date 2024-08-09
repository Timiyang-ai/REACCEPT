@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "",
        method = "read",
        args = {}
    )
    public void test_read() throws Exception {
        pis = new PipedInputStream();
        pos = new PipedOutputStream();

        try {
            pis.read();
            fail("Test 1: IOException expected since the stream is not connected.");
        } catch (IOException e) {
            // Expected.
        }
        
        pis.connect(pos);
        t = new Thread(pw = new PWriter(pos, 100));
        t.start();

        synchronized (pw) {
            pw.wait(5000);
        }
        assertEquals("Test 2: Unexpected number of bytes available. ", 
                     100, pis.available());
        
        for (int i = 0; i < 100; i++) {
            assertEquals("Test 3: read() returned incorrect byte. ", 
                         pw.bytes[i], (byte) pis.read());
        }

        try {
            pis.read();
            fail("Test 4: IOException expected since the thread that has " +
                 "written to the pipe is no longer alive.");
        } catch (IOException e) {
            // Expected.
        }

        pis.close();
        try {
            pis.read();
            fail("Test 5: IOException expected since the stream is closed.");
        } catch (IOException e) {
            // Expected.
        }
    }