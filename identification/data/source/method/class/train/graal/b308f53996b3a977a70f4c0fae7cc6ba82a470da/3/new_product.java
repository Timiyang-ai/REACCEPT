public FrameDescriptor copy() {
        CompilerAsserts.neverPartOfCompilation(NEVER_PART_OF_COMPILATION_MESSAGE);
        synchronized (lock) {
            FrameDescriptor clonedFrameDescriptor = new FrameDescriptor(this.defaultValue);
            for (int i = 0; i < slots.size(); i++) {
                FrameSlot slot = slots.get(i);
                clonedFrameDescriptor.addFrameSlot(slot.getIdentifier(), slot.getInfo(), FrameSlotKind.Illegal);
            }
            return clonedFrameDescriptor;
        }
    }