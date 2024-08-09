    @Test
    public void setConfigs() {
        WebTarget wt = target();
        try {
            wt.path("configuration/foo").request().post(
                    Entity.json("{ \"k\" : \"v\" }"), String.class);
        } catch (BadRequestException e) {
            assertEquals("incorrect key", "foo", service.component);
            assertEquals("incorrect key", "k", service.name);
            assertEquals("incorrect value", "v", service.value);
        }
    }