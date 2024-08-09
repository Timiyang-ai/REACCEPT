public List<LegacyAddress> getWatchedAddresses() {
        keyChainGroupLock.lock();
        try {
            List<LegacyAddress> addresses = new LinkedList<>();
            for (Script script : watchedScripts)
                if (ScriptPattern.isPayToPubKeyHash(script))
                    addresses.add(((LegacyAddress) script.getToAddress(params)));
            return addresses;
        } finally {
            keyChainGroupLock.unlock();
        }
    }