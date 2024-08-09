    @Test
    public void copy() {
        Object defaultValue = "default";
        FrameDescriptor d = new FrameDescriptor(defaultValue);
        s1 = d.addFrameSlot("v1", "i1", FrameSlotKind.Boolean);
        s2 = d.addFrameSlot("v2", "i2", FrameSlotKind.Float);

        assertEquals(2, d.getSize());
        assertEquals("i2", d.getSlots().get(1).getInfo());
        assertEquals(FrameSlotKind.Float, d.getFrameSlotKind(d.getSlots().get(1)));

        FrameDescriptor copy = d.copy();
        assertEquals(2, copy.getSize());
        assertEquals("Info is copied", "i2", copy.getSlots().get(1).getInfo());
        assertEquals("Kind isn't copied", FrameSlotKind.Illegal, copy.getFrameSlotKind(copy.getSlots().get(1)));
    }