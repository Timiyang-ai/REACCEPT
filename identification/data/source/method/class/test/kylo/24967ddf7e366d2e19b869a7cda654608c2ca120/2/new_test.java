    @WithMockJaasUser(username = "dladmin",
                      password = "secret",
                      authorities = {"admin", "user"})
    @Test
    public void findAll_NoMatchingGroupAclEntry() throws Exception {
        Pageable pageable = new PageRequest(0, 5);
        Page<? extends BatchJobExecution> all = repo.findAll(null, pageable);
    }