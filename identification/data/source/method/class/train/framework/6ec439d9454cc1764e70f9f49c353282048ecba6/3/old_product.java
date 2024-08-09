private void assertAttached(TestWindow win) {
        assertTrue("window attach not called", win.windowAttachCalled);
        assertTrue("window content attach not called", win.contentAttachCalled);
        assertTrue("window child attach not called", win.childAttachCalled);

        assertSame("window not attached", win.getApplication(), testApp);
        assertSame("window content not attached", win.getContent()
                .getApplication(), testApp);
        assertSame("window children not attached", win.getChild()
                .getApplication(), testApp);
    }