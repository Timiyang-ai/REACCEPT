@Test
    public void testSubstituteValues() {
        System.out.println("substituteValues");

        BackendAPI backend = EasyMock.mock(BackendAPI.class);

        EasyMock.reset(backend);

        Position machinePosition = new Position(1, 2, 3, UnitUtils.Units.MM);
        EasyMock.expect(backend.getMachinePosition()).andAnswer(() -> machinePosition);

        Position workPosition = new Position(4, 5, 6, UnitUtils.Units.MM);
        EasyMock.expect(backend.getWorkPosition()).andAnswer(() -> workPosition);

        EasyMock.replay(backend);

        String result = MacroHelper.substituteValues("{machine_x} {machine_y} {machine_z} {work_x} {work_y} {work_z}", backend);
        assertEquals("1 2 3 4 5 6", result);
    }