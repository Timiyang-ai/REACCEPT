public void unpark(Notifier notifier, WaitNotifyKey key) {
        WaitSetEntry entry = queue.peek();
        while (entry != null) {
            Operation op = entry.getOperation();
            if (notifier == op) {
                throw new IllegalStateException("Found cyclic wait-notify! -> " + notifier);
            }
            if (entry.isValid()) {
                if (entry.isExpired()) {
                    // expired
                    entry.onExpire();
                } else {
                    if (entry.shouldWait()) {
                        return;
                    }
                    OperationService operationService = nodeEngine.getOperationService();
                    operationService.run(op);
                }
                entry.setValid(false);
            }
            // consume
            queue.poll();

            entry = queue.peek();

            // If parkQueue.peek() returns null, we should deregister this specific
            // key to avoid memory leak. By contract we know that park() and unpark()
            // cannot be called in parallel.
            // We can safely remove this queue from registration map here.
            if (entry == null) {
                waitSetMap.remove(key);
            }
        }
    }