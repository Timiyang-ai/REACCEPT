    @Test
    public void oldBackupsToDeleteTest() throws Exception {
        // For each backup type, suppose we have 4 backups from that type and some other directories mixed in.
        // Then if our max number of backups is n, ensure 4-n of the oldest backups are deleted.
        // Also make sure we don't touch any of the other directories.
        for (ScheduledBackupType type : testedTypes) {
            String currBackupName = type.generateCurrentName(true);
            String monthOldBackupName = type.generateNameForTime(daysAgo(31), true);
            String twoMonthOldBackupName = type.generateNameForTime(daysAgo(62), true);
            String threeMonthOldBackupName = type.generateNameForTime(daysAgo(93), true);
            String[] existingBackups = new String[]{currBackupName, monthOldBackupName, twoMonthOldBackupName,
                                                    threeMonthOldBackupName, "live", "replica", "otherdirectory"};
            String desc = type.getDescription();
            for (int max = 1; max < 4; max++) {
                List<String> backupsToDelete = type.oldBackupsToDelete(existingBackups, existingBackups, max);
                assertEquals("should delete all but " + max + " backups for type " + desc, 4 - max, backupsToDelete.size());
                for (String backupToDelete : backupsToDelete) {
                    assertTrue("should delete a backup from type " + desc, type.isValidName(backupToDelete));
                    assertTrue("shouldn't delete the current backup for type " + desc, !backupToDelete.equals(currBackupName));
                }
            }
        }
    }