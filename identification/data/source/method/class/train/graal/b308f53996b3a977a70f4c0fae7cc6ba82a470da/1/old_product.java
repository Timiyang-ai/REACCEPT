public FrameDescriptor copy() {
        FrameDescriptor clonedFrameDescriptor = new FrameDescriptor(this.defaultValue);
        for (int i = 0; i < this.getSlots().size(); i++) {
            Object identifier = this.getSlots().get(i).getIdentifier();
            clonedFrameDescriptor.addFrameSlot(identifier);
        }
        return clonedFrameDescriptor;
    }