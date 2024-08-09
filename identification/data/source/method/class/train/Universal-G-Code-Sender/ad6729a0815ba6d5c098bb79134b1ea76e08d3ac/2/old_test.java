@Test
    public void testGetGrblStatusCapabilities() {
        System.out.println("getGrblStatusCapabilities");
        double version;
        String letter;
        Capabilities expResult;
        Capabilities result;

        version = 0.8;
        letter = "c";
        expResult = Capabilities.STATUS_C;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(expResult, result);
        
        version = 0.8;
        letter = "a";
        expResult = null;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(expResult, result);
        
        version = 0.9;
        letter = null;
        expResult = Capabilities.STATUS_C;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(expResult, result);
    }