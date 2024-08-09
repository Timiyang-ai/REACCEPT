    private String putTest(final File testFile) throws IOException, RestErrorException {
        final MultipartFile multiFile = createNewMultiFile(testFile);
        String dirName = null;
        try {
            dirName = repo.put(null, multiFile);
        } catch (final IOException e) {
            fail(e.getMessage());
        }
        Assert.assertNotNull(dirName);
        return dirName;
    }