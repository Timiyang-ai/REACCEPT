public void removeFrameSlot(Object identifier) {
        lock();
        try {
            FrameSlot slot = identifierToSlotMap.get(identifier);
            if (slot == null) {
                throw new IllegalArgumentException("no such frame slot: " + identifier);
            }
            slots.remove(slot);
            identifierToSlotMap.removeKey(identifier);
            updateVersion();
            getNotInFrameAssumption(identifier);
        } finally {
            unlock();
        }
    }