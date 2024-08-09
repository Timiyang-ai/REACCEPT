@Override
    public final SelectionKey register(Selector selector, int interestSet,
            Object attachment) throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!((interestSet & ~validOps()) == 0)) {
            throw new IllegalArgumentException();
        }

        synchronized (blockingLock) {
            if (isBlocking) {
                throw new IllegalBlockingModeException();
            }
            if (!selector.isOpen()) {
                if (0 == interestSet) {
                    // throw ISE exactly to keep consistency
                    throw new IllegalSelectorException();
                }
                // throw NPE exactly to keep consistency
                throw new NullPointerException();
            }
            SelectionKey key = keyFor(selector);
            if (null == key) {
                key = ((AbstractSelector) selector).register(this, interestSet,
                        attachment);
                keyList.add(key);
            } else {
                if (!key.isValid()) {
                    throw new CancelledKeyException();
                }
                key.interestOps(interestSet);
                key.attach(attachment);
            }
            return key;
        }
    }