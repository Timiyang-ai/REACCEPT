@Test(expected = IllegalArgumentException.class)
    public void testIsOneTrue_primitive_nullInput() {
        BooleanUtils.isOneTrue((boolean[]) null);
    }