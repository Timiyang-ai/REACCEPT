@Test
    public void testStreamCommands() {
        System.out.println("streamCommands");
        MockConnection mc = new MockConnection(mg.in, mg.out);
        GrblCommunicator instance = new GrblCommunicator(cb, asl, mc);
        String thirtyNineCharString = "thirty-nine character command here.....";

        boolean result;
        boolean expResult;
        
        // Make sure CommUtil is still an overly cautious jerk.
        LinkedList<GcodeCommand> l = new LinkedList<GcodeCommand>();
        l.add(new GcodeCommand("12characters"));
        assertEquals(13, CommUtils.getSizeOfBuffer(l));
        
        // Make sure GrblUtils hasn't updated RX buffer size.
        assertEquals(123, GrblUtils.GRBL_RX_BUFFER_SIZE);

        // Add a bunch of commands so that the buffer is full.
        // 39*3 + 3 newlines + 3 CommUtils caution  = 123 == buffer size.
        instance.queueStringForComm(thirtyNineCharString);
        instance.queueStringForComm(thirtyNineCharString);
        instance.queueStringForComm(thirtyNineCharString);
        
        // Stream them so that there are active commands.
        instance.streamCommands();
        expResult = true;
        result = instance.areActiveCommands();
        assertEquals(expResult, result);

        // Add another command and stream it so that something is queued.
        instance.queueStringForComm(thirtyNineCharString);
        instance.streamCommands();
        expResult = true;
        result = instance.areActiveCommands();
        assertEquals(expResult, result);

        // Check with MockGrbl to verify the fourth command wasn't sent.
        String output = mg.readStringFromGrblBuffer();
        assertEquals(thirtyNineCharString+"\n"+thirtyNineCharString+"\n"+thirtyNineCharString+"\n",
                        output);
        
        // Make room for the next command.
        try {
            mg.addOkFromGrbl();
        } catch (IOException e) {
            fail ("IOException in mock object: " + e.getMessage());
        }
        
        // Tell the instance that we have made data available.
        //instance.serialEvent(null);
        instance.responseMessage(null);

        instance.streamCommands();
        // Make sure the queued command was sent.
        output = mg.readStringFromGrblBuffer();
        assertEquals(thirtyNineCharString+"\n", output);

        
        // Tell the instance that we have made data available.
        //instance.serialEvent(null);
        instance.responseMessage(null);
        
        // Wrap up.
        try {
            mg.addOkFromGrbl();
            mg.addOkFromGrbl();
            mg.addOkFromGrbl();
        } catch (IOException e) {
            fail ("IOException in mock object: " + e.getMessage());
        }
        
        // Tell the instance that we have made data available.
        //instance.serialEvent(null);
        instance.responseMessage(null);

        expResult = false;
        result = instance.areActiveCommands();
        assertEquals(expResult, result);
    }