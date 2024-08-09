protected static History parseFileLog(Reader fileLog) throws IOException {
        List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
        HistoryEntry entry = null;
        try (BufferedReader reader = new BufferedReader(fileLog)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = REVISION_PATTERN.matcher(line);
                if (matcher.find()) {
                    /* An entry finishes when a new entry starts ... */
                    if (entry != null) {
                        entries.add(entry);
                        entry = null;
                    }
                    /* New entry */
                    entry = new HistoryEntry();
                    entry.setRevision(matcher.group(1));
                    int year = Integer.parseInt(matcher.group(2));
                    int month = Integer.parseInt(matcher.group(3));
                    int day = Integer.parseInt(matcher.group(4));
                    int hour = Integer.parseInt(matcher.group(5));
                    int minute = Integer.parseInt(matcher.group(6));
                    int second = Integer.parseInt(matcher.group(7));
                    entry.setDate(newDate(year, month, day, hour, minute, second));
                    entry.setAuthor(matcher.group(8));
                    entry.setActive(true);
                } else {
                    if (entry != null) {
                        /* ... an entry can also finish when some branch/edit entry is encountered */
                        if (line.startsWith("... ...")) {
                            entries.add(entry);
                            entry = null;
                        } else {
                            entry.appendMessage(line);
                        }
                    }
                }
            }
        }
        /* ... an entry can also finish when the log is finished */
        if (entry != null) {
            entries.add(entry);
        }

        History history = new History();
        history.setHistoryEntries(entries);
        return history;
    }