    private void assertDetached(TestContainer win) {
        assertUnattached(win);
        assertTrue("window detach not called", win.detachCalled());
        assertTrue("window content detach not called",
                win.getTestContent().contentDetachCalled);
        assertTrue("window child detach not called",
                win.getTestContent().childDetachCalled);
    }