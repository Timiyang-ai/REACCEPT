@Test
    public void data() throws Exception {
        double[] data = storage.data();
        assertNotNull(MathTestConstants.NULL_VAL, data);
        assertTrue(MathTestConstants.UNEXPECTED_VAL, data.length == MathTestConstants.STORAGE_SIZE *
            MathTestConstants.STORAGE_SIZE);
    }