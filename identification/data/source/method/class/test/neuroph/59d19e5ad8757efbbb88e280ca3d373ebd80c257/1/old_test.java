@Test
    public void testgetOutput() {
        instance.setSlope(1);
        assertEquals(expected, instance.getOutput(input), 0.0);
    }