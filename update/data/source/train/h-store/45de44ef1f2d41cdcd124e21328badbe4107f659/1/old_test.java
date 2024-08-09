@Test
    public void testGetProfileMeasurements() throws Exception {
        Collection<ProfileMeasurement> pms = profiler.getProfileMeasurements();
        assertNotNull(pms);
        assertEquals(4, pms.size());
    }