    public void tagEndsInTwoDashes() {
        Component c = readDesign("<vaadin-button--></vaadin-button-->");
        assertTrue(c.getClass() == Button.class);
    }