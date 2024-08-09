	@Test
	public void removeFirstTest() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.addLast(1);
		queue.addLast(2);
		queue.addLast(3);
		queue.addLast(4);

		assertEquals(4, queue.size);
		assertEquals(0, queue.indexOf(1, true));
		assertEquals(1, (Object)queue.removeFirst());

		assertEquals(3, queue.size);
		assertEquals(0, queue.indexOf(2, true));
		assertEquals(2, (Object)queue.removeFirst());

		assertEquals(2, queue.size);
		assertEquals(0, queue.indexOf(3, true));
		assertEquals(3, (Object)queue.removeFirst());

		assertEquals(1, queue.size);
		assertEquals(0, queue.indexOf(4, true));
		assertEquals(4, (Object)queue.removeFirst());

		assertEquals(0, queue.size);
	}