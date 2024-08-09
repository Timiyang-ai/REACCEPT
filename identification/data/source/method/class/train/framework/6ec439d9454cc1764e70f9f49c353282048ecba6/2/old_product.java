private void assertUnattached(TestContainer win) {
        assertSame("window not detached", win.getSession(), null);
        assertSame("window content not detached",
                getVaadinSession(win.getTestContent()), null);
        assertSame("window children not detached",
                getVaadinSession(win.getTestContent().child), null);
    }