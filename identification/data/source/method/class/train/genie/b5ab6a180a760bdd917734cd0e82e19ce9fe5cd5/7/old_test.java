@Test(expected = GenieNotFoundException.class)
    public void testUpdateDependenciesForCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        Mockito.when(this.jpaCommandRepository.findOne(id)).thenReturn(null);
        this.service.updateDependenciesForCommand(id, Sets.newHashSet());
    }