@Test
    public void testQueueStringForComm() throws Exception {
        
        System.out.println("queueStringForComm");
        String input = "someCommand";
        GrblCommunicator instance = new GrblCommunicator(mg.in, mg.out, cb, asl);
        
        try {
            instance.queueStringForComm(input);
            // The cb preloads commands so the size represents queued commands.
            assertEquals(1, cb.size());
            
            // Test that instance adds newline to improperly formed command.
            assertEquals(input + "\n", cb.peek());
            
            instance.queueStringForComm(input);
            instance.queueStringForComm(input);
            
            // Test that instance continues to queue inputs.
            assertEquals(3, cb.size());
            
            input = "someCommand\n";
            cb = new LinkedList<String>();
            instance = new GrblCommunicator(mg.in, mg.out, cb, asl);
            
            instance.queueStringForComm(input);
            // Test that instance doesn't add superfluous newlines.
            assertEquals(input, cb.peek());

        } catch (Exception e) {
            fail("queueStringForComm threw an exception: "+e.getMessage());
        }
    }