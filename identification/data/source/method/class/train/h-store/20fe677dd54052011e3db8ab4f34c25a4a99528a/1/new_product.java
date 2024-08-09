public static Histogram<String> getProcedureHistogram(File workload_path) throws Exception {
        final Histogram<String> h = new Histogram<String>();
        final String regex = "^\\{.*?,[\\s]*\"" +
                             AbstractTraceElement.Members.NAME.name() +
                             "\":[\\s]*\"([\\w\\d]+)\"[\\s]*,[\\s]*.*";
        final Pattern p = Pattern.compile(regex);

        if (debug.get()) LOG.debug("Fast generation of Procedure Histogram from Workload '" + workload_path.getAbsolutePath() + "'");
        BufferedReader reader = FileUtil.getReader(workload_path.getAbsolutePath());
        int line_ctr = 0;
        while (reader.ready()) {
            String line = reader.readLine();
            Matcher m = p.matcher(line);
            assert(m != null) : "Invalid Line #" + line_ctr + " [" + workload_path + "]\n" + line;
            assert(m.matches()) : "Invalid Line #" + line_ctr + " [" + workload_path + "]\n" + line;
            if (m.groupCount() > 0) {
                try {
                    h.put(m.group(1));
                } catch (IllegalStateException ex) {
                    LOG.error("Invalud Workload Line: " + line, ex);
                    System.exit(1);
                }
            } else {
                LOG.error("Invalid Workload Line: " + line);
                assert(m.groupCount() == 0);
            }
            line_ctr++;
        } // WHILE
        reader.close();
        
        if (debug.get()) LOG.debug("Processed " + line_ctr + " workload trace records for histogram\n" + h);
        return (h);
    }