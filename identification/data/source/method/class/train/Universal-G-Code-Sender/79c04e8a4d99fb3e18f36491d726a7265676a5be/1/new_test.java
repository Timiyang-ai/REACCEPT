@Test
    public void testGetGrblStatusCapabilities() {
        System.out.println("getGrblStatusCapabilities");
        double version;
        Character letter;
        Capabilities result;

        version = 0.8;
        letter = 'c';
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertTrue(result.hasCapability(GrblCapabilitiesConstants.REAL_TIME));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.V1_FORMAT));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.HARDWARE_JOGGING));
        assertFalse(result.hasOverrides());
        assertFalse(result.hasContinuousJogging());

        version = 0.8;
        letter = 'a';
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.REAL_TIME));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.V1_FORMAT));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.HARDWARE_JOGGING));
        assertFalse(result.hasOverrides());
        assertFalse(result.hasContinuousJogging());

        version = 0.9;
        letter = null;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertTrue(result.hasCapability(GrblCapabilitiesConstants.REAL_TIME));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.V1_FORMAT));
        assertFalse(result.hasCapability(GrblCapabilitiesConstants.HARDWARE_JOGGING));
        assertFalse(result.hasOverrides());
        assertFalse(result.hasContinuousJogging());

        version = 1.1;
        letter = null;
        result = GrblUtils.getGrblStatusCapabilities(version, letter);
        assertTrue(result.hasCapability(GrblCapabilitiesConstants.REAL_TIME));
        assertTrue(result.hasCapability(GrblCapabilitiesConstants.V1_FORMAT));
        assertTrue(result.hasCapability(GrblCapabilitiesConstants.HARDWARE_JOGGING));
        assertTrue(result.hasOverrides());
        assertTrue(result.hasContinuousJogging());
    }