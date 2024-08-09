    @Test
    public void deleteWithRetries() {
        // Mock process group
        final ProcessGroupDTO group = new ProcessGroupDTO();
        group.setId("d526adec-1f33-463b-8570-e9cf3e6c8703");
        group.setParentGroupId("93b1abbb-f805-4f52-8a9e-ffdab224dc44");

        // Mock NiFi Process Groups REST client
        final AbstractNiFiProcessGroupsRestClient client = Mockito.mock(AbstractNiFiProcessGroupsRestClient.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(client.doDelete(group)).thenThrow(new ClientErrorException(409)).thenReturn(Optional.empty());

        // Test delete
        Assert.assertEquals(Optional.empty(), client.deleteWithRetries(group, 1, 0, TimeUnit.NANOSECONDS));
    }