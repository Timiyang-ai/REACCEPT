    @Test
    public void topoOverlayFactory() {
        viewList = ImmutableList.of(HIDDEN_VIEW);
        ext = new UiExtension.Builder(cl, viewList)
                .topoOverlayFactory(TO_FACTORY)
                .build();

        assertNull("unexpected message handler factory", ext.messageHandlerFactory());
        assertEquals("wrong topo overlay factory", TO_FACTORY,
                     ext.topoOverlayFactory());
    }