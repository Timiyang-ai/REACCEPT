public List<Address> getWatchedAddresses() {
        keyChainGroupLock.lock();
        try {
            List<Address> addresses = new LinkedList<>();
            for (Script script : watchedScripts)
                if (ScriptPattern.isPayToPubKeyHash(script))
                    addresses.add(script.getToAddress(params));
            return addresses;
        } finally {
            keyChainGroupLock.unlock();
        }
    }