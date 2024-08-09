@Test
    public void testQueueStringForComm() throws Exception {
        
        System.out.println("queueStringForComm");
        String input = "someCommand";
        GrblCommunicator instance = new GrblCommunicator(mg.in, mg.out, gcb, acl);
        
        try {
            instance.queueStringForComm(input);
            // The gcb preloads commands so the size represents queued commands.
            assertEquals(0, gcb.size());
            
            // Test that instance adds newline to improperly formed command.
            assertEquals(input + "\n", gcb.currentCommand().getCommandString());
            
            instance.queueStringForComm(input);
            instance.queueStringForComm(input);
            
            // Test that instance continues to queue inputs.
            assertEquals(2, gcb.size());
            
            input = "someCommand\n";
            gcb = new GcodeCommandBuffer();
            instance = new GrblCommunicator(mg.in, mg.out, gcb, acl);
            
            instance.queueStringForComm(input);
            // Test that instance doesn't add superfluous newlines.
            assertEquals(input, gcb.currentCommand().getCommandString());

        } catch (Exception e) {
            fail("queueStringForComm threw an exception: "+e.getMessage());
        }
    }