@Test
	public void testEncodeDecode4() throws Exception {
		Message m1 = Utils2.createDummyMessage();
		Random rnd = new Random(42);
		m1.type(Message.Type.DENIED);
		m1.setHintSign();

		KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
		KeyPair pair1 = gen.generateKeyPair();
		m1.publicKeyAndSign(pair1);

		Map<Number640, Data> dataMap = new HashMap<Number640, Data>();
		dataMap.put(new Number640(rnd), new Data(new byte[] { 3, 4, 5 }));
		dataMap.put(new Number640(rnd), new Data(new byte[] { 4, 5, 6, 7 }));
		dataMap.put(new Number640(rnd), new Data(new byte[] { 5, 6, 7, 8, 9 }));
		m1.setDataMap(new DataMap(dataMap));
		NavigableMap<Number640, Collection<Number160>> keysMap = new TreeMap<Number640, Collection<Number160>>();
		Set<Number160> set = new HashSet<Number160>(1);
		set.add(new Number160(rnd));
		keysMap.put(new Number640(rnd), set);
		set = new HashSet<Number160>(2);
		set.add(new Number160(rnd));
		set.add(new Number160(rnd));
		keysMap.put(new Number640(rnd), set);
		set = new HashSet<Number160>(3);
		set.add(new Number160(rnd));
		set.add(new Number160(rnd));
		set.add(new Number160(rnd));
		keysMap.put(new Number640(rnd), set);
		m1.keyMap640Keys(new KeyMap640Keys(keysMap));

		Message m2 = encodeDecode(m1);
		Assert.assertEquals(true, m2.publicKey(0) != null);
		Assert.assertEquals(false, m2.dataMap(0) == null);
		Assert.assertEquals(false, m2.keyMap640Keys(0) == null);
		Assert.assertEquals(true, m2.verified());
		compareMessage(m1, m2);
	}