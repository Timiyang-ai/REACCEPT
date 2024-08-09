private void assertDetached(TestWindow win) {
        assertUnattached(win);
        assertTrue("window detach not called", win.windowDetachCalled);
        assertTrue("window content detach not called", win.contentDetachCalled);
        assertTrue("window child detach not called", win.childDetachCalled);
    }