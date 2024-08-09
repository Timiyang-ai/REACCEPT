@Test
    public void testRemoveBlob_TwoComplexBlobKeys() throws IOException {
        final String BLOB_KEY1 = TestUtils.createRandomBlobKey("aa/bb/cc/dd/", null);
        final String BLOB_KEY2 = TestUtils.createRandomBlobKey("aa/bb/ee/ff/", null);
        boolean result;

        blobStore.createContainerInLocation(null, CONTAINER_NAME);

        // checks that blob doesn't exist
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY1);
        assertFalse(result, "Blob1 exists");
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY2);
        assertFalse(result, "Blob2 exists");

        // create the blobs
        TestUtils.createBlobsInContainer(CONTAINER_NAME, BLOB_KEY1, BLOB_KEY2);
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY1);
        assertTrue(result, "Blob " + BLOB_KEY1 + " doesn't exist");
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY2);
        assertTrue(result, "Blob " + BLOB_KEY2 + " doesn't exist");

        // remove first blob
        blobStore.removeBlob(CONTAINER_NAME, BLOB_KEY1);
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY1);
        assertFalse(result, "Blob still exists");
        // first file deleted, not the second
        TestUtils.fileExists(TARGET_CONTAINER_NAME + File.separator + BLOB_KEY1, false);
        TestUtils.fileExists(TARGET_CONTAINER_NAME + File.separator + BLOB_KEY2, true);
        // only partial directory structure was removed, because it shares a path
        // with the second blob created
        TestUtils.directoryExists(TARGET_CONTAINER_NAME + "/aa/bb/cc/dd", false);
        TestUtils.directoryExists(TARGET_CONTAINER_NAME + "/aa/bb", true);
        // remove second blob
        blobStore.removeBlob(CONTAINER_NAME, BLOB_KEY2);
        result = blobStore.blobExists(CONTAINER_NAME, BLOB_KEY2);
        assertFalse(result, "Blob still exists");
        TestUtils.fileExists(TARGET_CONTAINER_NAME + File.separator + BLOB_KEY2, false);
        // now all the directory structure is empty
        TestUtils.directoryExists(TARGET_CONTAINER_NAME + "/aa", false);
    }