@Test
	public void testChangeProtectionKey() throws NoPeerConnectionException, IOException {
		KeyPair keypair1 = H2HEncryptionUtil.generateProtectionKeys();
		KeyPair keypair2 = H2HEncryptionUtil.generateProtectionKeys();

		Number160 locationKey = Number160.createHash(NetworkTestUtil.randomString());
		Number160 domainKey = H2HConstants.TOMP2P_DEFAULT_KEY;
		Number160 contentKey = Number160.createHash(NetworkTestUtil.randomString());

		NetworkManager node = network.get(random.nextInt(networkSize));

		// put some initial data with keypair1
		H2HTestData data1v0 = new H2HTestData(NetworkTestUtil.randomString());
		data1v0.generateVersionKey();
		data1v0.setBasedOnKey(Number160.ZERO);
		FuturePut putFuture1 = node.getDataManager().put(locationKey, domainKey, contentKey, data1v0,
				keypair1);
		putFuture1.awaitUninterruptibly();
		Assert.assertTrue(putFuture1.isSuccess());

		// put 1st version with keypair 1
		H2HTestData data1v1 = new H2HTestData(NetworkTestUtil.randomString());
		data1v1.generateVersionKey();
		data1v1.setBasedOnKey(data1v0.getVersionKey());
		FuturePut putFuture2 = node.getDataManager().put(locationKey, domainKey, contentKey, data1v1,
				keypair1);
		putFuture2.awaitUninterruptibly();
		Assert.assertTrue(putFuture2.isSuccess());

		// change protection key (during putting a new version)
		H2HTestData data1v2 = new H2HTestData(NetworkTestUtil.randomString());
		data1v2.generateVersionKey();
		data1v2.setBasedOnKey(data1v1.getVersionKey());
		FuturePut changeFuture1 = node.getDataManager().changeProtectionKey(locationKey, domainKey,
				contentKey, data1v2.getTimeToLive(), keypair1, keypair2);
		changeFuture1.awaitUninterruptibly();
		Assert.assertTrue(changeFuture1.isSuccess());

		// try to put a new version with the old protection key
		H2HTestData data1v3 = new H2HTestData(NetworkTestUtil.randomString());
		data1v3.generateVersionKey();
		data1v3.setBasedOnKey(data1v2.getVersionKey());
		FuturePut changeFuture2 = node.getDataManager().put(locationKey, domainKey, contentKey, data1v3,
				keypair1);
		changeFuture2.awaitUninterruptibly();
		Assert.assertFalse(changeFuture2.isSuccess());
	}