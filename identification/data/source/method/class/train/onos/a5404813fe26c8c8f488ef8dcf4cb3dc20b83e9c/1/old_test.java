    @Test
    @Ignore
    public void getAlarm() {
        WebTarget wt = target();
        String response = wt.path("/alarms/1").request().get(String.class);
        // Ensure hard-coded alarms returned okay
        assertThat(response, containsString("\"NE is not reachable\","));
        assertThat(response, not(containsString("\"Equipment Missing\",")));
    }