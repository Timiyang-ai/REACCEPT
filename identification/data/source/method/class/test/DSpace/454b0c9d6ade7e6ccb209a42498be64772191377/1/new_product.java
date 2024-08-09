public static Set<String> readPatterns(File patternFile)
            throws IOException
    {
        Set<String> patterns = new HashSet<String>();

        if (!patternFile.exists() || !patternFile.isFile())
        {
            return patterns;
        }

        //Read our file & get all them patterns.
        BufferedReader in = new BufferedReader(new FileReader(patternFile));
        String line;
        while ((line = in.readLine()) != null) {
            if (!line.startsWith("#")) {
                line = line.trim();

                if (!line.equals("")) {
                    patterns.add(line);
                }
            } else {
                //   ua.add(line.replaceFirst("#","").replaceFirst("UA","").trim());
                // ... add this functionality later
            }
        }
        in.close();
        return patterns;
    }