	@Test
	public void endsWith() {
		assertTrue(empty.endsWith(empty));
		assertTrue(fromString("hello").endsWith(fromString("ello")));
		assertFalse(fromString("hello").endsWith(fromString("ellov")));
		assertFalse(fromString("hello").endsWith(fromString("hhhello")));
		assertTrue(fromString("大千世界").endsWith(fromString("世界")));
		assertFalse(fromString("大千世界").endsWith(fromString("世")));
		assertFalse(fromString("数据砖头").endsWith(fromString("我的数据砖头")));
	}