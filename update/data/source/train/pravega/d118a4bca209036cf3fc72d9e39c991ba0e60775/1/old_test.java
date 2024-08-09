@Test
    public void testReplaceLastFile() {
        val expectedFiles = createFiles(0, 10);
        val lastFile = expectedFiles.get(expectedFiles.size() - 1);
        val validReplacement = new FileDescriptor(lastFile.getPath(), lastFile.getOffset(), lastFile.getLength() + 1, lastFile.getEpoch() + 1, true);
        val handle = HDFSSegmentHandle.write("foo", new ArrayList<>(expectedFiles));

        AssertExtensions.assertThrows(
                "removeLastFile did not fail when incorrect offset.",
                () -> handle.replaceLastFile(new FileDescriptor(lastFile.getPath(), lastFile.getOffset() + 1, lastFile.getLength(), lastFile.getEpoch(), true)),
                ex -> ex instanceof IllegalArgumentException);

        AssertExtensions.assertThrows(
                "removeLastFile did not fail when incorrect epoch.",
                () -> handle.replaceLastFile(new FileDescriptor(lastFile.getPath(), lastFile.getOffset(), lastFile.getLength(), lastFile.getEpoch() - 1, true)),
                ex -> ex instanceof IllegalArgumentException);

        handle.replaceLastFile(validReplacement);
        expectedFiles.set(expectedFiles.size() - 1, validReplacement);
        AssertExtensions.assertListEquals("Unexpected result from getFiles after replacing last file.",
                expectedFiles, handle.getFiles(), Object::equals);
    }