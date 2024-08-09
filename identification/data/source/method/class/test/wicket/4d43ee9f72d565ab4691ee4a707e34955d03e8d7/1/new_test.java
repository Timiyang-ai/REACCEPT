@Test
	public void getMinValue()
	{
		assertEquals(Integer.MIN_VALUE, Numbers.getMinValue(Integer.class));
		assertEquals(Integer.MIN_VALUE, Numbers.getMinValue(int.class));
		assertEquals(Long.MIN_VALUE, Numbers.getMinValue(Long.class));
		assertEquals(Long.MIN_VALUE, Numbers.getMinValue(long.class));
		assertEquals(-Float.MAX_VALUE, Numbers.getMinValue(Float.class));
		assertEquals(-Float.MAX_VALUE, Numbers.getMinValue(float.class));
		assertEquals(-Double.MAX_VALUE, Numbers.getMinValue(Double.class));
		assertEquals(-Double.MAX_VALUE, Numbers.getMinValue(double.class));
		assertEquals(Byte.MIN_VALUE, Numbers.getMinValue(Byte.class));
		assertEquals(Byte.MIN_VALUE, Numbers.getMinValue(byte.class));
		assertEquals(Short.MIN_VALUE, Numbers.getMinValue(Short.class));
		assertEquals(Short.MIN_VALUE, Numbers.getMinValue(short.class));
		assertEquals(-Double.MAX_VALUE, Numbers.getMinValue(BigDecimal.class));
		assertEquals(-Double.MAX_VALUE, Numbers.getMinValue(BigInteger.class));
		assertEquals(-Double.MAX_VALUE, Numbers.getMinValue(null));
	}