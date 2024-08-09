@Override
    public Set<URI> saveAttachments(
        final String jobId,
        final Set<Resource> attachments
    ) throws SaveAttachmentException {
        try {
            return this.writeAttachments(jobId, attachments);
        } catch (final IOException ioe) {
            throw new SaveAttachmentException(ioe);
        }
    }