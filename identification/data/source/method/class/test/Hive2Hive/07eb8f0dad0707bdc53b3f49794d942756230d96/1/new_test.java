@Test
	public void testChangeProtectionKey() throws NoPeerConnectionException, IOException, InvalidKeyException,
			SignatureException {
		KeyPair keypairOld = EncryptionUtil.generateRSAKeyPair();
		KeyPair keypairNew = EncryptionUtil.generateRSAKeyPair();

		Number160 locationKey = Number160.createHash(NetworkTestUtil.randomString());
		Number160 domainKey = H2HConstants.TOMP2P_DEFAULT_KEY;
		Number160 contentKey = Number160.createHash(NetworkTestUtil.randomString());

		NetworkManager node = network.get(random.nextInt(networkSize));

		// put some initial data
		H2HSharableTestData data = new H2HSharableTestData(NetworkTestUtil.randomString());
		data.generateVersionKey();
		data.setBasedOnKey(Number160.ZERO);
		FuturePut putFuture1 = node.getDataManager()
				.put(locationKey, domainKey, contentKey, data, keypairOld);
		putFuture1.awaitUninterruptibly();
		Assert.assertTrue(putFuture1.isSuccess());

		// change content protection key
		FuturePut changeFuture = node.getDataManager().changeProtectionKey(locationKey, domainKey,
				contentKey, data.getVersionKey(), data.getBasedOnKey(), data.getTimeToLive(), keypairOld,
				keypairNew, data.getHash());
		changeFuture.awaitUninterruptibly();
		Assert.assertTrue(changeFuture.isSuccess());

		// verify if content protection key has been changed
		Data resData = node.getDataManager().get(locationKey, domainKey, contentKey, data.getVersionKey())
				.awaitUninterruptibly().getData();
		Assert.assertTrue(resData.verify(keypairNew.getPublic(), new H2HSignatureFactory()));
	}