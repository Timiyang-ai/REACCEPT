@Nonnull
    protected Optional<ProcessGroupDTO> deleteWithRetries(@Nonnull final ProcessGroupDTO processGroup, final int retries) {
        schedule(processGroup.getId(), processGroup.getParentGroupId(), NiFiComponentState.STOPPED);

        try {
            return doDelete(processGroup);
        } catch (WebApplicationException e) {
            NifiClientRuntimeException clientException = new NifiClientRuntimeException(e);
            if (clientException.is409Error() && retries > 0) {
                try {
                    Thread.sleep(300);
                    return deleteWithRetries(processGroup, retries - 1);
                } catch (InterruptedException e2) {
                    throw new NifiClientRuntimeException("Unable to delete Process Group " + processGroup.getName(), e2);
                }
            } else {
                throw clientException;
            }
        }
    }