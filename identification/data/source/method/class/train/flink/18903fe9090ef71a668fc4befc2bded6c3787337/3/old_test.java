	@Test
	public void compareTo() {
		assertEquals(0, fromString("   ").compareTo(blankString(3)));
		assertTrue(fromString("").compareTo(fromString("a")) < 0);
		assertTrue(fromString("abc").compareTo(fromString("ABC")) > 0);
		assertTrue(fromString("abc0").compareTo(fromString("abc")) > 0);
		assertEquals(0, fromString("abcabcabc").compareTo(fromString("abcabcabc")));
		assertTrue(fromString("aBcabcabc").compareTo(fromString("Abcabcabc")) > 0);
		assertTrue(fromString("Abcabcabc").compareTo(fromString("abcabcabC")) < 0);
		assertTrue(fromString("abcabcabc").compareTo(fromString("abcabcabC")) > 0);

		assertTrue(fromString("abc").compareTo(fromString("世界")) < 0);
		assertTrue(fromString("你好").compareTo(fromString("世界")) > 0);
		assertTrue(fromString("你好123").compareTo(fromString("你好122")) > 0);

		MemorySegment segment1 = MemorySegmentFactory.allocateUnpooledSegment(1024);
		MemorySegment segment2 = MemorySegmentFactory.allocateUnpooledSegment(1024);
		SortUtil.putStringNormalizedKey(fromString("abcabcabc"), segment1, 0, 9);
		SortUtil.putStringNormalizedKey(fromString("abcabcabC"), segment2, 0, 9);
		assertTrue(segment1.compare(segment2, 0, 0, 9) > 0);
		SortUtil.putStringNormalizedKey(fromString("abcab"), segment1, 0, 9);
		assertTrue(segment1.compare(segment2, 0, 0, 9) < 0);
	}