@Test
	public void testRewind() {
		// state2.equals(state3)
		StateVertex state2 = new StateVertex("state2", "<table><div>state2</div></table>");
		StateVertex state3 = new StateVertex("state3", "<table><div>state2</div></table>");
		StateVertex state4 = new StateVertex("state4", "<table><div>state4</div></table>");
		/**
		 * Can not change to state2 because not inserted yet.
		 */
		assertFalse(sm.changeState(state2));
		assertNotSame(sm.getCurrentState(), state2);

		Eventable c = new Eventable(new Identification(How.xpath, "/bla"), EventType.click);
		assertTrue(sm.swithToStateAndCheckIfClone(c, state2, dummyBrowser, new CrawlSession(dummyPool)));

		/**
		 * Name is correctly changed
		 */
		assertEquals("State name changed correctly", "state1", state2.getName());

		// can not change to state2 because we are already in state2
		assertFalse(sm.changeState(state2));
		assertSame(sm.getCurrentState(), state2);

		// state2.equals(state3)
		assertEquals("state2 equals state3", state2, state3);

		// !state2.equals(state4)
		assertFalse("state2 not equals state4", state2.equals(state4));

		Eventable c2 = new Eventable(new Identification(How.xpath, "/bla2"), EventType.click);

		// False because its CLONE!
		assertFalse(sm.swithToStateAndCheckIfClone(c2, state3, dummyBrowser,
		        new CrawlSession(dummyPool)));

		Eventable c3 = new Eventable(new Identification(How.xpath, "/bla2"), EventType.click);

		// True because its not yet known
		assertTrue(sm
		        .swithToStateAndCheckIfClone(c3, state4, dummyBrowser, new CrawlSession(dummyPool)));

		sm.rewind();

		assertEquals("CurrentState == index", index, sm.getCurrentState());

		// Now we can go from index -> state2
		assertTrue(sm.changeState(state2));

		// Now we can go from state2 -> state2
		assertTrue(sm.changeState(state2));

		// Now we can go from state2 -> state4
		assertTrue(sm.changeState(state4));

		sm.rewind();

		assertEquals("CurrentState == index", index, sm.getCurrentState());

		// Now we can not go from index -> state4
		assertFalse(sm.changeState(state4));

	}