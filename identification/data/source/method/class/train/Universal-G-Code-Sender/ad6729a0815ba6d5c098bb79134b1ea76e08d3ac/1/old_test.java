@Test
    public void testGetStateFromStatusString() {
        System.out.println("getStateFromStatusString");
        String status;
        Capabilities version;
        String expResult;
        String result;

        status = "<Idle,MPos:5.529,0.560,7.000,WPos:1.529,-5.440,-0.000>";
        version = Capabilities.STATUS_C;
        expResult = "Idle";
        result = GrblUtils.getStateFromStatusString(status, version);
        assertEquals(expResult, result);
    }