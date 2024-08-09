public boolean delete(ApplicationId applicationId) {
        Tenant tenant = tenantRepository.getTenant(applicationId.tenant());
        if (tenant == null) return false;

        TenantApplications tenantApplications = tenant.getApplicationRepo();
        if (!tenantApplications.listApplications().contains(applicationId)) return false;

        // Deleting an application is done by deleting the remote session and waiting
        // until the config server where the deployment happened picks it up and deletes
        // the local session
        long sessionId = tenantApplications.getSessionIdForApplication(applicationId);
        RemoteSession remoteSession = getRemoteSession(tenant, sessionId);
        remoteSession.createDeleteTransaction().commit();

        log.log(LogLevel.INFO, TenantRepository.logPre(applicationId) + "Waiting for session " + sessionId + " to be deleted");
        // TODO: Add support for timeout in request
        Duration waitTime = Duration.ofSeconds(60);
        if (localSessionHasBeenDeleted(applicationId, sessionId, waitTime)) {
            log.log(LogLevel.INFO, TenantRepository.logPre(applicationId) + "Session " + sessionId + " deleted");
        } else {
            log.log(LogLevel.ERROR, TenantRepository.logPre(applicationId) + "Session " + sessionId + " was not deleted (waited " + waitTime + ")");
            return false;
        }

        NestedTransaction transaction = new NestedTransaction();
        transaction.add(new Rotations(tenant.getCurator(), tenant.getPath()).delete(applicationId)); // TODO: Not unit tested
        // (When rotations are updated in zk, we need to redeploy the zone app, on the right config server
        // this is done asynchronously in application maintenance by the node repository)
        transaction.add(tenantApplications.deleteApplication(applicationId));

        hostProvisioner.ifPresent(provisioner -> provisioner.remove(transaction, applicationId));
        transaction.onCommitted(() -> log.log(LogLevel.INFO, "Deleted " + applicationId));
        transaction.commit();

        return true;
    }