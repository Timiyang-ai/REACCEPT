@Test(expected = GenieNotFoundException.class)
    public void testAddDependenciesForCommand() throws GenieException {
        final String id = UUID.randomUUID().toString();
        Mockito.when(this.jpaCommandRepository.findOne(id)).thenReturn(null);
        this.service.addDependenciesForCommand(id, Sets.newHashSet());
    }