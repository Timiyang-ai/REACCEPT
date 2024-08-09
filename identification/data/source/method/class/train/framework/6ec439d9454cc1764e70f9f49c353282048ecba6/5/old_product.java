private void assertUnattached(TestContainer win) {
        assertSame("window not detached", win.getApplication(), null);
        assertSame("window content not detached", win.getTestContent()
                .getApplication(), null);
        assertSame("window children not detached",
                win.getTestContent().child.getApplication(), null);
    }