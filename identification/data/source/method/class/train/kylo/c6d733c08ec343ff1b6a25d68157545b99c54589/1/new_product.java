@Nonnull
    protected Optional<ProcessGroupDTO> deleteWithRetries(@Nonnull final ProcessGroupDTO processGroup, final int retries, final int timeout, @Nonnull final TimeUnit timeUnit) {
        // Stop the process group
        schedule(processGroup.getId(), processGroup.getParentGroupId(), NiFiComponentState.STOPPED);

        // Try to delete the process group
        Exception lastError = null;

        for (int count=0; count <= retries; ++count) {
            try {
                return doDelete(processGroup);
            } catch (final WebApplicationException e) {
                if (e.getResponse().getStatus() == 409) {
                    lastError = e;
                    Uninterruptibles.sleepUninterruptibly(timeout, timeUnit);
                } else {
                    throw new NifiClientRuntimeException(e);
                }
            }
        }

        // Give up
        throw new NifiClientRuntimeException("Unable to delete process group: " + processGroup.getId(), lastError);
    }