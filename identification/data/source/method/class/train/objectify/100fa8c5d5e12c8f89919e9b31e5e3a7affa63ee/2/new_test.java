@Test
	void testTransactionless() throws Exception {
		factory().register(Thing.class);

		for (int i=1; i<10; i++) {
			final Thing th = new Thing(i);
			ofy().save().entity(th).now();
		}

		ofy().transact(() -> {
			for (int i=1; i<10; i++) {
				final int index = i;
				ofy().transactionless(() -> ofy().load().type(Thing.class).id(index).now());
			}

			ofy().save().entity(new Thing(99));
		});
	}