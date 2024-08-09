@Test
    public void testIssueSoftReset() throws IOException {
        System.out.println("issueSoftReset");
        GrblController instance = new GrblController(mgc);
        
        // Noop if called while comm is closed.
        instance.issueSoftReset();
        // Did not send reset command to communicator or issue reset.
        assertEquals(0x0, mgc.sentByte);
        assertEquals(0, mgc.numSoftResetCalls);
        
        try {
            instance.openCommPort("blah", 1234);
        } catch (Exception e) {
            fail("Unexpected exception from GrblController: " + e.getMessage());
        }
        
        // Automatic soft reset
        assertEquals(24, mgc.sentByte);
        assertEquals(0, mgc.numSoftResetCalls);

        // Enable real time mode by sending correct GRBL version:
        instance.rawResponseHandler("Grbl 0.8c");
        instance.issueSoftReset();
        // Sent reset command to communicator and issued reset.
        assertEquals(GrblUtils.GRBL_RESET_COMMAND, mgc.sentByte);
        assertEquals(1, mgc.numSoftResetCalls);
        
        // GRBL version that might not have the command but I send it to anyway:
        mgc.resetInputsAndFunctionCalls();
        instance.rawResponseHandler("Grbl 0.8a");
        instance.issueSoftReset();
        // Sent reset command to communicator and issued reset.
        assertEquals(GrblUtils.GRBL_RESET_COMMAND, mgc.sentByte);
        assertEquals(1, mgc.numSoftResetCalls);
        
        // GRBL version that should not be sent the command:
        mgc.resetInputsAndFunctionCalls();
        instance.rawResponseHandler("Grbl 0.7");
        instance.issueSoftReset();
        // Sent reset command to communicator and issued reset.
        assertEquals(0x0, mgc.sentByte);
        assertEquals(0, mgc.numSoftResetCalls);
    }