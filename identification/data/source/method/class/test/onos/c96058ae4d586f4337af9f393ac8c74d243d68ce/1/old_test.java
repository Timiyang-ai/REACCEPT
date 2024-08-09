    @Test
    public void valueOf() {
        IntentId id = new IntentId(0xdeadbeefL);
        assertEquals("incorrect valueOf", id, IntentId.valueOf(0xdeadbeefL));
    }