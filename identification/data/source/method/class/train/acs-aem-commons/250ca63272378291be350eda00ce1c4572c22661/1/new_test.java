    @Test
    public void testisRichText_PlainText() {
        boolean expResult = false;
        boolean result = TextUtil.isRichText("This is is not rich text");
        assertEquals(expResult, result);
    }