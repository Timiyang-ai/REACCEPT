	@Test
	public void substring() {
		assertEquals(empty, fromString("hello").substring(0, 0));
		assertEquals(fromString("el"), fromString("hello").substring(1, 3));
		assertEquals(fromString("数"), fromString("数据砖头").substring(0, 1));
		assertEquals(fromString("据砖"), fromString("数据砖头").substring(1, 3));
		assertEquals(fromString("头"), fromString("数据砖头").substring(3, 5));
		assertEquals(fromString("ߵ梷"), fromString("ߵ梷").substring(0, 2));
	}