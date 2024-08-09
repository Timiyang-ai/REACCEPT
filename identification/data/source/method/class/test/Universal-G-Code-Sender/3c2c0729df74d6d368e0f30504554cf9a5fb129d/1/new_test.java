@Test
    public void testQueueStringForComm() throws Exception {
        
        System.out.println("queueStringForComm");
        String input = "someCommand";
        MockConnection mc = new MockConnection(mg.in, mg.out);
        GrblCommunicator instance = new GrblCommunicator(cb, asl, mc);
        
        try {
            instance.queueStringForComm(input);
            // The cb preloads commands so the size represents queued commands.
            assertEquals(1, cb.size());
            
            // Test that instance adds newline to improperly formed command.
            String next = cb.peek();
            assertEquals(input, cb.peek());
            
            instance.queueStringForComm(input);
            instance.queueStringForComm(input);
            
            // Test that instance continues to queue inputs.
            assertEquals(3, cb.size());
            
            input = "someCommand";
            cb = new LinkedBlockingDeque<>();
            mc = new MockConnection(mg.in, mg.out);
            instance = new GrblCommunicator(cb, asl, mc);

            instance.queueStringForComm(input);
            // Test that instance doesn't add superfluous newlines.
            assertEquals(input, cb.peek());

        } catch (Exception e) {
            fail("queueStringForComm threw an exception: "+e.getMessage());
        }
    }