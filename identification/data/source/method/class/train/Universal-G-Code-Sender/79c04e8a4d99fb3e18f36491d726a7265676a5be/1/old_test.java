@Test
    public void testGetGrblStatusCapabilities() {
        System.out.println("getGrblStatusCapabilities");
        double version;
        Character letter;
        Capabilities expResult = new Capabilities();
        Capabilities result;

        version = 0.8;
        letter = 'c';
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(true, result.REAL_TIME);
        assertEquals(false, result.OVERRIDES);
        
        version = 0.8;
        letter = 'a';
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(false, result.REAL_TIME);
        assertEquals(false, result.OVERRIDES);
        
        version = 0.9;
        letter = null;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(true, result.REAL_TIME);
        assertEquals(false, result.OVERRIDES);

        version = 1.1;
        letter = null;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertEquals(true, result.REAL_TIME);
        assertEquals(true, result.OVERRIDES);
        assertEquals(true, result.V1_FORMAT);
    }