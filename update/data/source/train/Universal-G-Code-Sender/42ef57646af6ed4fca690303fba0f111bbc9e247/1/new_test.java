@Test
    public void testSetLineTerminator() {
        System.out.println("setLineTerminator");
        GrblCommunicator instance = new GrblCommunicator();
        String defaultTerminator = AbstractCommunicator.DEFAULT_TERMINATOR;
        
        // Initial value.
        assertEquals(defaultTerminator, instance.getLineTerminator());
        
        instance.setLineTerminator("tada");
        assertEquals("tada", instance.getLineTerminator());
        
        instance.setLineTerminator(null);
        assertEquals(defaultTerminator, instance.getLineTerminator());
        
        instance.setLineTerminator("");
        assertEquals(defaultTerminator, instance.getLineTerminator());
    }