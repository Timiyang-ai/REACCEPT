@Test
    public void setBorderTop() {
        assertEquals(NONE, getCellStyle(0, 0).getBorderTopEnum());
        assertEquals(NONE, getCellStyle(0, 1).getBorderTopEnum());
        assertEquals(NONE, getCellStyle(0, 2).getBorderTopEnum());
        RegionUtil.setBorderTop(THIN, A1C3, sheet);
        assertEquals(THIN, getCellStyle(0, 0).getBorderTopEnum());
        assertEquals(THIN, getCellStyle(0, 1).getBorderTopEnum());
        assertEquals(THIN, getCellStyle(0, 2).getBorderTopEnum());
    }