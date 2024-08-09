@Test
    public void testToCharArray() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(ArrayUtils.EMPTY_CHAR_ARRAY, sb.toCharArray());

        char[] a = sb.toCharArray();
        assertNotNull("toCharArray() result is null", a);
        assertEquals("toCharArray() result is too large", 0, a.length);

        sb.append("junit");
        a = sb.toCharArray();
        assertEquals("toCharArray() result incorrect length", 5, a.length);
        assertTrue("toCharArray() result does not match", Arrays.equals("junit".toCharArray(), a));
    }