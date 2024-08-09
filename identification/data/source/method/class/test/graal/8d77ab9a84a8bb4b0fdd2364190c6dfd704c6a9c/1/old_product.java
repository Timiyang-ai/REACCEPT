public void removeFrameSlot(Object identifier) {
        CompilerAsserts.neverPartOfCompilation("interpreter-only.  includes hashmap operations.");
        assert identifierToSlotMap.containsKey(identifier);
        slots.remove(identifierToSlotMap.get(identifier));
        identifierToSlotMap.remove(identifier);
        updateVersion();
        getNotInFrameAssumption(identifier);
    }