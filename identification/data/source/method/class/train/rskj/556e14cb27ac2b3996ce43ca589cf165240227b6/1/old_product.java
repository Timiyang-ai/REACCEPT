public static byte[] serializeLockWhitelist(LockWhitelist whitelist) {
        return serializeBtcAddresses(whitelist.getAddresses());
    }