    @Test
    public void addPushApplication() {
        PushApplication pa = new PushApplication();
        pa.setName("EJB Container");
        final String uuid = UUID.randomUUID().toString();
        pa.setPushApplicationID(uuid);

        pushApplicationService.addPushApplication(pa);

        PushApplication stored = pushApplicationService.findByPushApplicationID(uuid);
        assertThat(stored).isNotNull();
        assertThat(stored.getId()).isNotNull();
        assertThat(pa.getName()).isEqualTo(stored.getName());
        assertThat(pa.getPushApplicationID()).isEqualTo(stored.getPushApplicationID());
    }