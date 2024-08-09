@Test
    public void testStreamCommands() {
        System.out.println("streamCommands");
        MockConnection mc = new MockConnection(mg.in, mg.out);
        GrblCommunicator instance = new GrblCommunicator(cb, asl, mc);
        String term = instance.getLineTerminator();
        String thirtyNineCharString = "thirty-nine character command here.....";

        boolean result;
        boolean expResult;
        
        // Make sure CommUtil is still an overly cautious jerk.
        LinkedList<GcodeCommand> l = new LinkedList<>();
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
        String goal = thirtyNineCharString+term+thirtyNineCharString+term+thirtyNineCharString+term;
        assertEquals(goal, output);
        
        // Make room for the next command.
        mc.sendResponse("ok");
        
        // Send it.
        instance.streamCommands();
        
        // Make sure the queued command was sent.
        output = mg.readStringFromGrblBuffer();
        assertEquals(thirtyNineCharString+term, output);
  
        // Wrap up.
        mc.sendResponse("ok");
        mc.sendResponse("ok");
        mc.sendResponse("ok");

        expResult = false;
        result = instance.areActiveCommands();
        assertEquals(expResult, result);
    }