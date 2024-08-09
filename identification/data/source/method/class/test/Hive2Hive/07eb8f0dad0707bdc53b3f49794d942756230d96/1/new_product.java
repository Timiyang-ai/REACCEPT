public FuturePut changeProtectionKey(Number160 locationKey, Number160 domainKey, Number160 contentKey,
			Number160 versionKey, Number160 basedOnKey, int ttl, KeyPair oldProtectionKey,
			KeyPair newProtectionKey, byte[] hash) {
		logger.debug(String
				.format("change content protection key location key = '%s' domain key = '%s' content key = '%s' version key '%s'",
						locationKey, domainKey, contentKey, versionKey));
		try {
			// create dummy object to change the protection key
			Data data = new Data().ttlSeconds(ttl).basedOn(basedOnKey);

			// encrypt hash with new key pair to get the new signature (without having the data object)
			Cipher rsa = Cipher.getInstance("RSA");
			rsa.init(Cipher.ENCRYPT_MODE, newProtectionKey.getPrivate());
			byte[] newSignature = rsa.doFinal(hash);

			// sign duplicated meta (don't forget to set signed flag)
			data = data.signature(signatureCodec.decode(newSignature)).signed(true).duplicateMeta();

			// change the protection key through a put meta
			return getPeer().put(locationKey).setDomainKey(domainKey).putMeta().setData(contentKey, data)
					.setVersionKey(versionKey).keyPair(oldProtectionKey).start();
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			logger.error(String
					.format("Change protection key failed. location key = '%s' domain key = '%s' content key = '%s' version key = '%s' exception = '%s'",
							locationKey, domainKey, contentKey, versionKey, e.getMessage()));
			return null;
		}
	}