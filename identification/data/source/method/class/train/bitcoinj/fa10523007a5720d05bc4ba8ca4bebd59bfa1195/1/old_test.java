@Test
    public void testRun_exception() throws Exception {
        conn.exceptionOnRead(new IOException("done"));
        try {
            peer.run();
            fail("did not throw");
        } catch (PeerException e) {
            assertTrue(e.getCause() instanceof IOException);
        }
    }