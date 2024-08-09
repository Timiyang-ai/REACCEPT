@Test(expected = GenieNotFoundException.class)
    public void testGetDependenciesForCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        Mockito.when(this.jpaCommandRepository.findOne(id)).thenReturn(null);
        this.service.getDependenciesForCommand(id);
    }