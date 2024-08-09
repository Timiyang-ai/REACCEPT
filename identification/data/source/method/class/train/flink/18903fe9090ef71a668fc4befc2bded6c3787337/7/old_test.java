	@Test
	public void indexOf() {
		assertEquals(0, empty.indexOf(empty, 0));
		assertEquals(-1, empty.indexOf(fromString("l"), 0));
		assertEquals(0, fromString("hello").indexOf(empty, 0));
		assertEquals(2, fromString("hello").indexOf(fromString("l"), 0));
		assertEquals(3, fromString("hello").indexOf(fromString("l"), 3));
		assertEquals(-1, fromString("hello").indexOf(fromString("a"), 0));
		assertEquals(2, fromString("hello").indexOf(fromString("ll"), 0));
		assertEquals(-1, fromString("hello").indexOf(fromString("ll"), 4));
		assertEquals(1, fromString("数据砖头").indexOf(fromString("据砖"), 0));
		assertEquals(-1, fromString("数据砖头").indexOf(fromString("数"), 3));
		assertEquals(0, fromString("数据砖头").indexOf(fromString("数"), 0));
		assertEquals(3, fromString("数据砖头").indexOf(fromString("头"), 0));
	}