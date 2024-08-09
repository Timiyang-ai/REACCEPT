    @Test
    public void backupAllBooks() throws Exception {
        String activeBookUID = createNewBookWithDefaultAccounts();
        BookUtils.activateBook(activeBookUID);
        createNewBookWithDefaultAccounts();
        assertThat(mBooksDbAdapter.getRecordsCount()).isEqualTo(2);

        BackupManager.backupAllBooks();

        for (String bookUID : mBooksDbAdapter.getAllBookUIDs()) {
            assertThat(BackupManager.getBackupList(bookUID).size()).isEqualTo(1);
        }
    }