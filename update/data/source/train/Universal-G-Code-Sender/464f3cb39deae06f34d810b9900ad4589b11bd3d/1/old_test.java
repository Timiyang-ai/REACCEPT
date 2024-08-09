@Test
    public void testOpenCommPort() {
        System.out.println("openCommPort/isCommOpen");
        String port = "serialPort";
        int portRate = 12345;
        GrblController instance = new GrblController(mgc);
        Boolean expResult = true;
        Boolean result = false;
        try {
            result = instance.openCommPort(port, portRate);
        } catch (Exception e) {
            fail("Unexpected exception from GrblController: " + e.getMessage());
        }
        assertEquals(expResult, result);
        assertEquals(expResult, instance.isCommOpen());
        assertEquals(port, mgc.portName);
        assertEquals(portRate, mgc.portRate);
        
        String exception = "";
        // Check exception trying to open the comm port twice.
        try {
            instance.openCommPort(port, portRate);
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("Comm port is already open.", exception);
    }