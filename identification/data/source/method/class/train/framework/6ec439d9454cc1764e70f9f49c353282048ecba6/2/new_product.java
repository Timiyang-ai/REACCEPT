private void assertUnattached(TestContainer win) {
        assertSame("window not detached", win.getSession(), null);
        assertSame("window content not detached",
                getSession(win.getTestContent()), null);
        assertSame("window children not detached",
                getSession(win.getTestContent().child), null);
    }