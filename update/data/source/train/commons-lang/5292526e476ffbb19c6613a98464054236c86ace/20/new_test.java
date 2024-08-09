@Test
    public void testEquals_Object() {
        final CharRange rangea = CharRange.is('a');
        final CharRange rangeae = CharRange.isIn('a', 'e');
        final CharRange rangenotbf = CharRange.isIn('b', 'f');

        assertFalse(rangea.equals(null));

        assertTrue(rangea.equals(rangea));
        assertTrue(rangea.equals(CharRange.is('a')));
        assertTrue(rangeae.equals(rangeae));
        assertTrue(rangeae.equals(CharRange.isIn('a', 'e')));
        assertTrue(rangenotbf.equals(rangenotbf));
        assertTrue(rangenotbf.equals(CharRange.isIn('b', 'f')));

        assertFalse(rangea.equals(rangeae));
        assertFalse(rangea.equals(rangenotbf));
        assertFalse(rangeae.equals(rangea));
        assertFalse(rangeae.equals(rangenotbf));
        assertFalse(rangenotbf.equals(rangea));
        assertFalse(rangenotbf.equals(rangeae));
    }