@Test
	public void testEncodeDecode2() throws Exception { // encode
		Random rnd = new Random(42);
		Message m1 = Utils2.createDummyMessage();
		m1.command((byte) 3);
		m1.type(Message.Type.DENIED);
		Number160 key1 = new Number160(5667);
		Number160 key2 = new Number160(5667);
		m1.key(key1);
		m1.key(key2);
		List<Number640> tmp2 = new ArrayList<Number640>();
		Number640 n1 = new Number640(rnd);
		Number640 n2 = new Number640(rnd);
		tmp2.add(n1);
		tmp2.add(n2);
		m1.keyCollection(new KeyCollection(tmp2));

		Message m2 = encodeDecode(m1);

		Assert.assertEquals(false, m2.keyList() == null);
		Assert.assertEquals(false, m2.keyCollectionList() == null);
		compareMessage(m1, m2);
	}