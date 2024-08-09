@Test
    public void testDelete() {
        expect(mockVnetAdminService.getTenantIds())
                .andReturn(ImmutableSet.of(tenantId2)).anyTimes();
        mockVnetAdminService.unregisterTenantId(anyObject());
        expectLastCall();
        replay(mockVnetAdminService);

        WebTarget wt = target()
                .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        Response response = wt.path("tenants/" + tenantId2)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();

        assertThat(response.getStatus(), is(HttpURLConnection.HTTP_NO_CONTENT));

        verify(mockVnetAdminService);
    }