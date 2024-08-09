@Test
	public void testChangeState() {
		StateVertex state2 = new StateVertex("state2", "<table><div>state2</div></table>");

		/**
		 * Can not change index because not added.
		 */
		assertFalse(sm.changeState(state2));
		assertNotSame(sm.getCurrentState(), state2);

		/**
		 * Add index.
		 */
		Eventable c = new Eventable(new Identification(How.xpath, "/bla"), EventType.click);
		assertTrue(sm.updateAndCheckIfClone(c, state2, dummyBrowser, new CrawlSession(dummyPool)));

		/**
		 * Name is correctly changed
		 */
		assertEquals("State name changed correctly", "state1", state2.getName());

		/**
		 * Current index is the new index
		 */
		assertEquals(sm.getCurrentState(), state2);

		/**
		 * Change back.
		 */
		assertTrue(sm.changeState(index));
		assertEquals(sm.getCurrentState(), index);
	}