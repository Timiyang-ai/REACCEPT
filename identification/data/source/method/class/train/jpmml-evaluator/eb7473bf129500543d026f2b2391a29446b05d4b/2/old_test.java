	@Test
	public void cast(){
		assertEquals(1, TypeUtil.cast(DataType.INTEGER, (byte)1));
		assertEquals(1, TypeUtil.cast(DataType.INTEGER, (short)1));
		assertEquals(1, TypeUtil.cast(DataType.INTEGER, 1));
		assertEquals(1, TypeUtil.cast(DataType.INTEGER, 1l));
		assertEquals(1, TypeUtil.cast(DataType.INTEGER, true));

		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, (byte)1));
		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, (short)1));
		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, 1));
		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, 1l));
		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, 1f));
		assertEquals(1f, TypeUtil.cast(DataType.FLOAT, true));

		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, (byte)1));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, (short)1));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, 1));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, 1l));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, 1f));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, 1d));
		assertEquals(1d, TypeUtil.cast(DataType.DOUBLE, true));
	}