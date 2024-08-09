protected static History parseChanges(Reader fileHistory) throws IOException {
        /* OUTPUT:
        Directory changelog:
        Change 177601 on 2008/02/12 by user@host 'description'
         */
        History history = new History();
        List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
        try (BufferedReader reader = new BufferedReader(fileHistory)) {
            String line;
            Matcher matcher = CHANGE_PATTERN.matcher("");
            while ((line = reader.readLine()) != null) {
                matcher.reset(line);
                if (matcher.find()) {
                    HistoryEntry entry = new HistoryEntry();
                    entry.setRevision(matcher.group(1));
                    int year = Integer.parseInt(matcher.group(2));
                    int month = Integer.parseInt(matcher.group(3));
                    int day = Integer.parseInt(matcher.group(4));
                    int hour = Integer.parseInt(matcher.group(5));
                    int minute = Integer.parseInt(matcher.group(6));
                    int second = Integer.parseInt(matcher.group(7));
                    entry.setDate(newDate(year, month, day, hour, minute, second));
                    entry.setAuthor(matcher.group(8));
                    entry.setMessage(matcher.group(9).trim());
                    entry.setActive(true);
                    entries.add(entry);
                }
            }
        }
        history.setHistoryEntries(entries);
        return history;
    }