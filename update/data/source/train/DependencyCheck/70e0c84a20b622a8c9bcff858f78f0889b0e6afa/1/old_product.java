public static Model readPom(File file) throws AnalysisException {
        Model model = null;
        try {
            PomParser parser = new PomParser();
            parser.parse(file);
        } catch (PomParseException ex) {
            final String msg = String.format("Unable to parse pom '%s'", file.getPath());
            LOGGER.log(Level.WARNING, msg);
            LOGGER.log(Level.FINE, "", ex);
            throw new AnalysisException(ex);
        } catch (IOException ex) {
            final String msg = String.format("Unable to parse pom '%s'(IO Exception)", file.getPath());
            LOGGER.log(Level.WARNING, msg);
            LOGGER.log(Level.FINE, "", ex);
            throw new AnalysisException(ex);
        } catch (Throwable ex) {
            final String msg = String.format("Unexpected error during parsing of the pom '%s'", file.getPath());
            LOGGER.log(Level.WARNING, msg);
            LOGGER.log(Level.FINE, "", ex);
            throw new AnalysisException(ex);
        }
        return model;
    }