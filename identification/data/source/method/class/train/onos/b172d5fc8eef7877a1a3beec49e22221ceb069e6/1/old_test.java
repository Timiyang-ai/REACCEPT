    @Test
    public void valueOf() {
        final AlarmId id = AlarmId.alarmId(DEVICE_ID, UNIQUE_ID_1);
        assertEquals("incorrect valueOf", id, ID_A);
    }