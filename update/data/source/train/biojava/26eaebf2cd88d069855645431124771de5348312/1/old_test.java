@Test
    public void testHashCode() {
        System.out.println("hashCode");
        Hsp instance;
        int expResult;
        int result;
        
        instance = hspImpl;
        expResult = 71782805;
        result = instance.hashCode();
        assertEquals(expResult, result);
        
        instance = uncompleteHsp;
        expResult = 679;
        result = instance.hashCode();
        assertEquals(expResult, result);
        
        Hsp uncompleteHsp2 = new BlastHspBuilder()
                .setPercentageIdentity(100.00/100)
                .setHspAlignLen(48)
                .setMismatchCount(0)
                .setHspGaps(0)
                .setHspQueryFrom(1)
                .setHspQueryTo(48)
                .setHspHitFrom(344)
                .setHspHitTo(391)
                .setHspEvalue(4e-19)
                .setHspBitScore(95.6)
                .createBlastHsp();
        
        assertFalse(uncompleteHsp.hashCode() == uncompleteHsp2.hashCode());
    }