private void assertAttached(TestContainer win) {
        TestContent testContent = win.getTestContent();

        assertTrue("window attach not called", win.attachCalled());
        assertTrue("window content attach not called",
                testContent.contentAttachCalled);
        assertTrue("window child attach not called",
                testContent.childAttachCalled);

        assertSame("window not attached", win.getApplication(), testApp);
        assertSame("window content not attached", testContent.getApplication(),
                testApp);
        assertSame("window children not attached",
                testContent.child.getApplication(), testApp);
    }