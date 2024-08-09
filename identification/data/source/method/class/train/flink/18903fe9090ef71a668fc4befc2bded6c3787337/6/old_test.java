	@Test
	public void contains() {
		assertTrue(empty.contains(empty));
		assertTrue(fromString("hello").contains(fromString("ello")));
		assertFalse(fromString("hello").contains(fromString("vello")));
		assertFalse(fromString("hello").contains(fromString("hellooo")));
		assertTrue(fromString("大千世界").contains(fromString("千世界")));
		assertFalse(fromString("大千世界").contains(fromString("世千")));
		assertFalse(fromString("大千世界").contains(fromString("大千世界好")));
	}