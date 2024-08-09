@Test
    public void testGetIntfNameFromPciAddress() {

        String expectedIntfName1 = "enp5s8";
        String expectedIntfName2 = "enp5s8f3";

        assertNull(getIntfNameFromPciAddress(openstackPort));
        assertEquals(expectedIntfName1, getIntfNameFromPciAddress(openstackSriovPort1));
        assertEquals(expectedIntfName2, getIntfNameFromPciAddress(openstackSriovPort2));
        assertEquals(UNSUPPORTED_VENDOR, getIntfNameFromPciAddress(openstackSriovPort3));
    }