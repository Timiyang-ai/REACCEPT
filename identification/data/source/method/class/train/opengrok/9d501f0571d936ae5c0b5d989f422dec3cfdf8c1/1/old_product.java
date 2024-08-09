protected static History parseChanges(Reader fileHistory) throws IOException {
        /* OUTPUT:
        Directory changelog:
        Change 177601 on 2008/02/12 by user@host 'description'
         */
        History history = new History();
        List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
        BufferedReader reader = new BufferedReader(fileHistory);
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = CHANGE_PATTERN.matcher(line);
            if (matcher.find()) {
                HistoryEntry entry = new HistoryEntry();
                entry.setRevision(matcher.group(1));
                int year = Integer.parseInt(matcher.group(2));
                int month = Integer.parseInt(matcher.group(3));
                int day = Integer.parseInt(matcher.group(4));
                Calendar calendar = new GregorianCalendar(year, month, day);
                entry.setDate(calendar.getTime());
                entry.setAuthor(matcher.group(5));
                entry.setMessage(matcher.group(6).trim());
                entry.setActive(true);
                entries.add(entry);
            }
        }
        reader.close();
        history.setHistoryEntries(entries);
        return history;
    }