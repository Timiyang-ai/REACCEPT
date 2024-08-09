@Test
    public void testGetStateFromStatusString() {
        System.out.println("getStateFromStatusString");
        String status;
        Capabilities version = new Capabilities();
        String expResult;
        String result;

        status = "<Idle,MPos:5.529,0.560,7.000,WPos:1.529,-5.440,-0.000>";
        version.REAL_TIME = true;
        expResult = "Idle";
        result = GrblUtils.getStateFromStatusString(status, version);
        assertEquals(expResult, result);
    }