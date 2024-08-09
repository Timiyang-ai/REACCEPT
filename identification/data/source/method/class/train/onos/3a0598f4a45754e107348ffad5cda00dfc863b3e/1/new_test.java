    @Test
    public void buttons() {
        basic();
        pp.addButton(BD_A)
                .addButton(BD_B);
        assertEquals("wrong buttons", 2, pp.buttons().size());
        verifyButtons(KEY_A, KEY_B);

        pp.removeButtons(BD_B)
                .addButton(BD_C)
                .addButton(BD_Z);
        assertEquals("wrong buttons", 3, pp.buttons().size());
        verifyButtons(KEY_A, KEY_C, KEY_Z);

        pp.removeAllButtons()
                .addButton(BD_B);
        assertEquals("wrong buttons", 1, pp.buttons().size());
        verifyButtons(KEY_B);
    }