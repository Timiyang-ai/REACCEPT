@Test
    public void testRelease()
    {
        
        server.occupie();
        server.release();
        assertTrue(server.isFree());
    }