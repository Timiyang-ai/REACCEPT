@Test(expected = GenieNotFoundException.class)
    public void testUpdateDependenciesForCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        Mockito.when(this.jpaCommandRepository.findByUniqueId(id)).thenReturn(Optional.empty());
        this.service.updateDependenciesForCommand(id, Sets.newHashSet());
    }