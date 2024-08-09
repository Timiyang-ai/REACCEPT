public List<Address> getWatchedAddresses() {
        lock.lock();
        try {
            List<Address> addresses = new LinkedList<Address>();
            for (Script script : watchedScripts)
                if (script.isSentToAddress())
                    addresses.add(script.getToAddress(params));
            return addresses;
        } finally {
            lock.unlock();
        }
    }