@Test
    public void testSubstituteValues() {
        System.out.println("substituteValues");

        BackendAPI backend = EasyMock.mock(BackendAPI.class);

        EasyMock.reset(backend);
        final Capture<SystemStateBean> capture = EasyMock.newCapture();
        backend.updateSystemState(EasyMock.capture(capture));
        EasyMock.expect(EasyMock.expectLastCall()).andAnswer(() -> {
            capture.getValue().setMachineX("1");
            capture.getValue().setMachineY("2");
            capture.getValue().setMachineZ("3");
            capture.getValue().setWorkX("4");
            capture.getValue().setWorkY("5");
            capture.getValue().setWorkZ("6");
            return null;
        });
        EasyMock.replay(backend);

        String result = MacroHelper.substituteValues("{machine_x} {machine_y} {machine_z} {work_x} {work_y} {work_z}", backend);
        assertEquals("1 2 3 4 5 6", result);
    }