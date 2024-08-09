@Override
  public synchronized void delete(final Id.Namespace namespaceId) throws Exception {
    final NamespaceId namespace = namespaceId.toEntityId();
    // TODO: CDAP-870, CDAP-1427: Delete should be in a single transaction.
    NamespaceMeta namespaceMeta = get(namespaceId);

    if (checkProgramsRunning(namespaceId.toEntityId())) {
      throw new NamespaceCannotBeDeletedException(namespaceId,
                                                  String.format("Some programs are currently running in namespace " +
                                                                  "'%s', please stop them before deleting namespace",
                                                                namespaceId));
    }

    authorizationEnforcer.enforce(namespace, authenticationContext.getPrincipal(), Action.ADMIN);

    LOG.info("Deleting namespace '{}'.", namespace);
    try {
      resourceDeleter.get().deleteResources(namespaceMeta);

      // Delete the namespace itself, only if it is a non-default namespace. This is because we do not allow users to
      // create default namespace, and hence deleting it may cause undeterministic behavior.
      // Another reason for not deleting the default namespace is that we do not want to call a delete on the default
      // namespace in the storage provider (Hive, HBase, etc), since we re-use their default namespace.
      if (!NamespaceId.DEFAULT.equals(namespace)) {
        // Finally delete namespace from MDS and remove from cache
        deleteNamespaceMeta(namespaceId.toEntityId());

        // revoke privileges as the final step. This is done in the end, because if it is done before actual deletion,
        // and deletion fails, we may have a valid (or invalid) namespace in the system, that no one has privileges on,
        // so no one can clean up. This may result in orphaned privileges, which will be cleaned up by the create API
        // if the same namespace is successfully re-created.
        privilegesManager.revoke(namespace);
        LOG.info("Namespace '{}' deleted", namespace);
      } else {
        LOG.info("Keeping the '{}' namespace after removing all data.", NamespaceId.DEFAULT);
      }
    } catch (Exception e) {
      LOG.warn("Error while deleting namespace {}", namespaceId, e);
      throw new NamespaceCannotBeDeletedException(namespaceId, e);
    }
  }