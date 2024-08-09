    @Test
    public void equals() {
        assertTrue(ObjectUtils.equals(1, 1));
        assertTrue(ObjectUtils.equals("str", "str"));
        assertTrue(ObjectUtils.equals(null, null));

        assertFalse(ObjectUtils.equals(null, 1));
        assertFalse(ObjectUtils.equals(null, ""));
    }