	@Test
	public void removeLast () {
		list.iter();
		assertEquals(Integer.valueOf(1), list.next());
		list.removeLast();
		assertEquals(Integer.valueOf(2), list.next());
		assertNull(list.next());
	}