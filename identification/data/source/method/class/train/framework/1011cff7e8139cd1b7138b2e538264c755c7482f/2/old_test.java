    public void tagEndsInDash() {
        Component c = readDesign("<vaadin-button-></vaadin-button->");
        assertTrue(c.getClass() == Button.class);
    }