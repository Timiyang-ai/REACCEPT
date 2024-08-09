public void removeFrameSlot(Object identifier) {
        CompilerAsserts.neverPartOfCompilation(NEVER_PART_OF_COMPILATION_MESSAGE);
        if (!identifierToSlotMap.containsKey(identifier)) {
            throw new IllegalArgumentException("no such frame slot: " + identifier);
        }
        slots.remove(identifierToSlotMap.get(identifier));
        identifierToSlotMap.removeKey(identifier);
        updateVersion();
        getNotInFrameAssumption(identifier);
    }