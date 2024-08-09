@Test(expected = GenieNotFoundException.class)
    public void testGetDependenciesForCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        Mockito.when(this.jpaCommandRepository.findByUniqueId(id)).thenReturn(Optional.empty());
        this.service.getDependenciesForCommand(id);
    }