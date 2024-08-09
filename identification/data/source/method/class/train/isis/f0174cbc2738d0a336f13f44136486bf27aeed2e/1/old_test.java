    @Test
    public void getMemberNaturalName() {
        interactionEvent = new InteractionEvent(source, identifier) {

            
        };
        assertThat(interactionEvent.getMemberNaturalName(), equalTo("Cancel Order"));
    }