@Override
    public Set<URI> saveAttachments(final String jobId, final Set<Resource> attachments) throws IOException {
        return this.writeAttachments(jobId, attachments);
    }