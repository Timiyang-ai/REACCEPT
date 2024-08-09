private void assertUnattached(TestWindow win) {
        assertSame("window not detached", win.getApplication(), null);
        assertSame("window content not detached", win.getContent()
                .getApplication(), null);
        assertSame("window children not detached", win.getChild()
                .getApplication(), null);
    }