public boolean isConsistent() {
        lock.lock();
        try {
            boolean success = true;
            Set<Transaction> transactions = getTransactions(true);

            Set<Sha256Hash> hashes = new HashSet<Sha256Hash>();
            for (Transaction tx : transactions) {
                hashes.add(tx.getHash());
            }

            int size1 = transactions.size();

            if (size1 != hashes.size()) {
                log.error("Two transactions with same hash");
                success = false;
            }

            int size2 = unspent.size() + spent.size() + pending.size() + dead.size();
            if (size1 != size2) {
                log.error("Inconsistent wallet sizes: {} {}", size1, size2);
                success = false;
            }

            for (Transaction tx : unspent.values()) {
                if (!tx.isConsistent(this, false)) {
                    success = false;
                    log.error("Inconsistent unspent tx {}", tx.getHashAsString());
                }
            }

            for (Transaction tx : spent.values()) {
                if (!tx.isConsistent(this, true)) {
                    success = false;
                    log.error("Inconsistent spent tx {}", tx.getHashAsString());
                }
            }

            if (!success) {
                try {
                    log.error(toString());
                } catch (RuntimeException x) {
                    log.error("Printing inconsistent wallet failed", x);
                }
            }
            return success;
        } finally {
            lock.unlock();
        }
    }