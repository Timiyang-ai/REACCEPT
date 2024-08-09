@Test
    public void testFormatDisplayText() {
        // Stop names
        assertEquals("SDSU Transit Center", UIUtils.formatDisplayText("SDSU Transit Center"));
        assertEquals("VA Hospital", UIUtils.formatDisplayText("VA Hospital"));
        assertEquals("SDSU", UIUtils.formatDisplayText("SDSU"));
        assertEquals("UTC Transit Center", UIUtils.formatDisplayText("UTC Transit Center"));

        // Trip headsigns
        assertEquals("North to University Area TC",
                UIUtils.formatDisplayText("North to University Area TC"));
        assertEquals("North To University Area Tc",
                UIUtils.formatDisplayText("NORTH TO UNIVERSITY AREA TC"));
        assertEquals("SDSU", UIUtils.formatDisplayText("SDSU"));

        // Route names
        assertEquals("Downtown San Diego - UTC via Old Town",
                UIUtils.formatDisplayText("Downtown San Diego - UTC via Old Town"));
        assertEquals("UTC/VA Med CTR Express",
                UIUtils.formatDisplayText("UTC/VA Med CTR Express"));
    }