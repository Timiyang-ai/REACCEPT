    private Set<File> saveAttachments(final String jobId) throws GenieException, IOException {
        final Set<File> attachments = Sets.newHashSet();
        for (int i = 0; i < 10; i++) {
            final File original = saveAttachment(jobId);
            final File saved = new File(
                this.folder.getRoot().getAbsolutePath() + "/" + jobId + "/" + original.getName()
            );
            Assert.assertTrue(saved.exists());
            attachments.add(saved);
        }
        return attachments;
    }