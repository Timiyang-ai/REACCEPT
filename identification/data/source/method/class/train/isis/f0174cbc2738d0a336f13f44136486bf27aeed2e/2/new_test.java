    @Test
    public void getClassNaturalName() {
        interactionEvent = new InteractionEvent(source, identifier) {

            
        };
        assertThat(interactionEvent.getClassNaturalName(), equalTo("Customer Order"));
    }