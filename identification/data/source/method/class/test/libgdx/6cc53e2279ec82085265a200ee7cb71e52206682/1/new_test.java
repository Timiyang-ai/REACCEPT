	@Test
	public void remove () {
		list.iter();
		list.next(); // 1
		list.remove();
		list.next(); // 2
		list.next(); // 3
		list.remove();
		list.iter();
		assertEquals(Integer.valueOf(2), list.next());
		assertNull(list.next());
	}