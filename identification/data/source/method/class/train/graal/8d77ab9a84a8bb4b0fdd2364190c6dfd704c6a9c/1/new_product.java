public void removeFrameSlot(Object identifier) {
        CompilerAsserts.neverPartOfCompilation(NEVER_PART_OF_COMPILATION_MESSAGE);
        FrameSlot slot = identifierToSlotMap.get(identifier);
        if (slot == null) {
            throw new IllegalArgumentException("no such frame slot: " + identifier);
        }
        slots.remove(slot);
        identifierToSlotMap.removeKey(identifier);
        updateVersion();
        getNotInFrameAssumption(identifier);
    }