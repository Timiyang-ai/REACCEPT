public static byte[] makeRowKey(String applicationName, short applicationType, long time) {
		if (applicationName == null) {
			throw new NullPointerException("applicationName must not be null");
		}

		byte[] applicationnameBytes = Bytes.toBytes(applicationName);
		byte[] applicationnameBytesLength = Bytes.toBytes((short) applicationnameBytes.length);
		// byte[] offset = new byte[HBaseTables.APPLICATION_NAME_MAX_LEN - applicationnameBytes.length];
		byte[] applicationtypeBytes = Bytes.toBytes(applicationType);
		byte[] slot = Bytes.toBytes(time);

		return BytesUtils.concat(applicationnameBytesLength, applicationnameBytes, applicationtypeBytes, slot);
	}