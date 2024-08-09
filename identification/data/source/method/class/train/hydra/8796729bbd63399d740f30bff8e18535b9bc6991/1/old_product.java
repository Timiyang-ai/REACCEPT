public List<String> oldBackupsToDelete(String[] allBackups, String[] completeBackups, int max) {
        List<String> validBackupsOfThisType = getSortedListBackupsOfThisType(completeBackups);
        List<String> allBackupsOfThisType = getSortedListBackupsOfThisType(allBackups);
        List<String> rv = new ArrayList<String>();
        if (max < 0) // Indicator that we're not sure how many backups should be created
        {
            return rv;
        }
        for (String backup : allBackupsOfThisType) {
            if (!validBackupsOfThisType.contains(backup)) {
                rv.add(backup); // Delete invalid backups
            }
        }
        int excess = validBackupsOfThisType.size() - max;
        if (excess <= 0 || validBackupsOfThisType.isEmpty()) {
            return rv;
        }
        if (protectedBackupTypes.containsKey(this)) {
            String latestValidBackup = validBackupsOfThisType.get(validBackupsOfThisType.size() - 1);
            try {
                rv.addAll(validBackupsOfThisType.subList(0, excess));
            } catch (IllegalArgumentException e) {
                log.warn("Failed to parse date from backup with name " + latestValidBackup);
            }
        } else {
            rv.addAll(validBackupsOfThisType.subList(0, excess));
        }
        return rv;
    }