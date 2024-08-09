    @Test
    public void findAllPushApplicationsForDeveloper() {

        assertThat(searchApplicationService.findAllPushApplicationsForDeveloper(0, 10).getResultList()).isEmpty();

        PushApplication pa = new PushApplication();
        pa.setName("EJB Container");
        final String uuid = UUID.randomUUID().toString();
        pa.setPushApplicationID(uuid);
        pa.setDeveloper("admin");

        pushApplicationService.addPushApplication(pa);

        assertThat(searchApplicationService.findAllPushApplicationsForDeveloper(0, 10).getResultList()).isNotEmpty();
        assertThat(searchApplicationService.findAllPushApplicationsForDeveloper(0, 10).getResultList()).hasSize(1);
    }