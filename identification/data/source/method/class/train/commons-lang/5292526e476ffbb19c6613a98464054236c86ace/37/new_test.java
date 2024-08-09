@Test
    public void testContains_Char() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");
        
        assertFalse(btod.contains('a'));
        assertTrue(btod.contains('b'));
        assertTrue(btod.contains('c'));
        assertTrue(btod.contains('d'));
        assertFalse(btod.contains('e'));
        
        assertFalse(bcd.contains('a'));
        assertTrue(bcd.contains('b'));
        assertTrue(bcd.contains('c'));
        assertTrue(bcd.contains('d'));
        assertFalse(bcd.contains('e'));
        
        assertFalse(bd.contains('a'));
        assertTrue(bd.contains('b'));
        assertFalse(bd.contains('c'));
        assertTrue(bd.contains('d'));
        assertFalse(bd.contains('e'));
        
        assertTrue(notbtod.contains('a'));
        assertFalse(notbtod.contains('b'));
        assertFalse(notbtod.contains('c'));
        assertFalse(notbtod.contains('d'));
        assertTrue(notbtod.contains('e'));
        
        assertFalse(dtob.contains('a'));
        assertTrue(dtob.contains('b'));
        assertTrue(dtob.contains('c'));
        assertTrue(dtob.contains('d'));
        assertFalse(dtob.contains('e'));
      
        final CharRange[] array = dtob.getCharRanges();
        assertEquals("[b-d]", dtob.toString());
        assertEquals(1, array.length);
    }