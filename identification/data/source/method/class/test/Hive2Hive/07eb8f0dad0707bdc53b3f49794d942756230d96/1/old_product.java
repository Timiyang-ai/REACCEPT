public FuturePut changeProtectionKey(Number160 locationKey, Number160 domainKey, Number160 contentKey,
			int ttl, KeyPair oldProtectionKey, KeyPair newProtectionKey) {
		logger.debug(String.format(
				"change protection key; location key = '%s' domain key = '%s' content key = '%s'",
				locationKey, domainKey, contentKey));
		try {

			if (newProtectionKey == null) {
				logger.error("Cannot change the protection key to null value");
				return null;
			} else {
				// create dummy object to change the protection key
				Data data = new Data();
				data.ttlSeconds(ttl);

				// create a meta duplicate
				data = data.setProtectedEntry().sign(newProtectionKey, signatureFactory).duplicateMeta();

				// the content will be protected after this put
				if (oldProtectionKey == null) {
					// the content is protected for the first time
					return getPeer().put(locationKey).setData(contentKey, data).setDomainKey(domainKey)
							.keyPair(newProtectionKey).start();
				} else {
					// change the protection keys
					return getPeer().put(locationKey).setData(contentKey, data).setDomainKey(domainKey)
							.keyPair(oldProtectionKey).start();
				}
			}
		} catch (IOException | InvalidKeyException | SignatureException e) {
			logger.error(String
					.format("Change the protection key failed. location key = '%s' domain key = '%s' content key = '%s' exception = '%s'",
							locationKey, domainKey, contentKey, e.getMessage()));
			return null;
		}
	}