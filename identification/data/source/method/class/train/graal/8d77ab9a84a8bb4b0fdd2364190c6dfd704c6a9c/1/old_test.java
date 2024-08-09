    @Test
    public void removeFrameSlot() throws FrameSlotTypeException {
        TruffleRuntime runtime = Truffle.getRuntime();
        FrameDescriptor frameDescriptor = new FrameDescriptor();
        FrameSlot slot1 = frameDescriptor.addFrameSlot("var1", FrameSlotKind.Object);
        FrameSlot slot2 = frameDescriptor.addFrameSlot("var2", FrameSlotKind.Object);
        Frame frame = runtime.createMaterializedFrame(new Object[0], frameDescriptor);
        frame.setObject(slot1, "a");
        frame.setObject(slot2, "b");
        assertEquals("a", frame.getObject(slot1));
        assertEquals("b", frame.getObject(slot2));
        assertEquals(2, frameDescriptor.getSize());

        frameDescriptor.removeFrameSlot("var1");
        assertNull(frameDescriptor.findFrameSlot("var1"));
        assertEquals("b", frame.getObject(slot2));
        assertEquals(2, frameDescriptor.getSize());
        assertEquals(1, frameDescriptor.copy().getSize());

        FrameSlot slot3 = frameDescriptor.addFrameSlot("var3", FrameSlotKind.Object);
        FrameSlot slot4 = frameDescriptor.addFrameSlot("var4", FrameSlotKind.Object);
        assertEquals("b", frame.getObject(slot2));
        assertEquals(null, frame.getObject(slot3));
        assertEquals(null, frame.getObject(slot4));
        assertEquals(4, frameDescriptor.getSize());
        assertEquals(3, frameDescriptor.copy().getSize());

        frame.setObject(slot3, "c");
        frame.setObject(slot4, "d");
        assertEquals("b", frame.getObject(slot2));
        assertEquals("c", frame.getObject(slot3));
        assertEquals("d", frame.getObject(slot4));
    }