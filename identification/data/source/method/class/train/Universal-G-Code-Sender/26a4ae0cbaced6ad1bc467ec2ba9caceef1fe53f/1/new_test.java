@Test
    public void testStatusStringListener() {
        System.out.println("statusStringListener");
        String state = "";
        Position machineCoord = null;
        Position workCoord = null;
        GUIBackend instance = new GUIBackend();
        instance.statusStringListener(new ControllerStatus(state, machineCoord, workCoord));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }