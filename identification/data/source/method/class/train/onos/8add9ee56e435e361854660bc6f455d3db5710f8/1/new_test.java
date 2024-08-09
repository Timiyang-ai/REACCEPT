    @Test
    public void messageHandlerFactory() {
        viewList = ImmutableList.of(FOO_VIEW);
        ext = new UiExtension.Builder(cl, viewList)
                .messageHandlerFactory(MH_FACTORY)
                .build();

        assertEquals("wrong message handler factory", MH_FACTORY,
                     ext.messageHandlerFactory());
        assertNull("unexpected topo overlay factory", ext.topoOverlayFactory());
    }