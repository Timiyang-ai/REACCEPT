private ArrayList<LogEntry> logs(final IOFile file) throws QueryException {
    try(final NewlineInput nli = new NewlineInput(file)) {
      final ArrayList<LogEntry> logs = new ArrayList<>();
      for(String line; (line = nli.readLine()) != null;) {
        final LogEntry log = new LogEntry();
        final String[] cols = line.split("\t");
        if(cols.length > 2 && (cols[1].matches(".*:\\d+") || cols[1].equals(Log.SERVER))) {
          log.time = cols[0];
          log.address = cols[1];
          log.user = cols[2];
          log.type = cols.length > 3 ? cols[3] : "";
          log.message = cols.length > 4 ? cols[4] : "";
          log.ms = cols.length > 5 ? new BigDecimal(cols[5].replace(" ms", "")) : BigDecimal.ZERO;
        } else {
          // legacy format
          log.message = line;
        }
        logs.add(log);
      }
      return logs;
    } catch(final IOException ex) {
      throw IOERR_X.get(info, ex);
    }
  }