    @Test
    void filter() {
        assertTrue(OBT.filter(b -> true).getAsBoolean());
        assertFalse(OBT.filter(b -> false).isPresent());

        assertFalse(OBF.filter(b -> true).getAsBoolean());
        assertFalse(OBF.filter(b -> false).isPresent());

        assertFalse(OBE.filter(b -> true).isPresent());
        assertFalse(OBE.filter(b -> false).isPresent());
    }