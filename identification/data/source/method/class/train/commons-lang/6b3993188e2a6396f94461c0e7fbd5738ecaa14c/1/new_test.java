@Test(expected = IllegalArgumentException.class)
    public void testIsExactlyOneTrue_primitive_nullInput() {
        BooleanUtils.isExactlyOneTrue((boolean[]) null);
    }