@Test
    public void testGetProfileMeasurements() throws Exception {
        ProfileMeasurement pms[] = profiler.getProfileMeasurements();
        assertNotNull(pms);
        assertEquals(4, pms.length);
        for (int i = 0; i < pms.length; i++) {
            assertNotNull(Integer.toString(i), pms[i]);
        } // FOR
    }