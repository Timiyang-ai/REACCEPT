    @Test
    public void putTest() throws Exception {
        Step step = new Step();
        stepStorage.put(step);
        assertTrue(step == stepStorage.getLast());
    }