    @Test
    public void delete() {
        {
            PrepareResult result = deployApp(testApp);
            long sessionId = result.sessionId();
            Tenant tenant = tenantRepository.getTenant(applicationId().tenant());
            LocalSession applicationData = tenant.getLocalSessionRepo().getSession(sessionId);
            assertNotNull(applicationData);
            assertNotNull(applicationData.getApplicationId());
            assertNotNull(tenant.getRemoteSessionRepo().getSession(sessionId));
            assertNotNull(applicationRepository.getActiveSession(applicationId()));

            // Delete app and verify that it has been deleted from repos and provisioner
            assertTrue(applicationRepository.delete(applicationId()));
            assertNull(applicationRepository.getActiveSession(applicationId()));
            assertNull(tenant.getLocalSessionRepo().getSession(sessionId));
            assertNull(tenant.getRemoteSessionRepo().getSession(sessionId));
            assertTrue(provisioner.removed);
            assertEquals(tenant.getName(), provisioner.lastApplicationId.tenant());
            assertEquals(applicationId(), provisioner.lastApplicationId);

            assertFalse(applicationRepository.delete(applicationId()));
        }

        {
            deployApp(testApp);
            assertTrue(applicationRepository.delete(applicationId()));
            deployApp(testApp);

            // Deploy another app (with id fooId)
            ApplicationId fooId = applicationId(tenant2);
            PrepareParams prepareParams2 = new PrepareParams.Builder().applicationId(fooId).build();
            deployApp(testApp, prepareParams2);
            assertNotNull(applicationRepository.getActiveSession(fooId));

            // Delete app with id fooId, should not affect original app
            assertTrue(applicationRepository.delete(fooId));
            assertEquals(fooId, provisioner.lastApplicationId);
            assertNotNull(applicationRepository.getActiveSession(applicationId()));

            assertTrue(applicationRepository.delete(applicationId()));
        }
    }