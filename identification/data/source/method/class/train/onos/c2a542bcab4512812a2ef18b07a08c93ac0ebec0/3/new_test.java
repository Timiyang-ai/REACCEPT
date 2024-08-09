    @Test
    public void unsetConfigs() {
        WebTarget wt = target();
        try {
            // TODO: this needs to be revised later. Do you really need to
            // contain any entry inside delete request? Why not just use put then?
            wt.path("configuration/foo").request().delete();
        } catch (BadRequestException e) {
            assertEquals("incorrect key", "foo", service.component);
            assertEquals("incorrect key", "k", service.name);
            assertEquals("incorrect value", null, service.value);
        }
    }