public static Model readPom(File file) throws AnalysisException {
        try {
            final PomParser parser = new PomParser();
            final Model model = parser.parse(file);
            if (model == null) {
                throw new AnalysisException(String.format("Unable to parse pom '%s'", file.getPath()));
            }
            return model;
        } catch (PomParseException ex) {
            LOGGER.warn("Unable to parse pom '{}'", file.getPath());
            LOGGER.debug("", ex);
            throw new AnalysisException(ex);
        } catch (IOException ex) {
            LOGGER.warn("Unable to parse pom '{}'(IO Exception)", file.getPath());
            LOGGER.debug("", ex);
            throw new AnalysisException(ex);
        } catch (Throwable ex) {
            LOGGER.warn("Unexpected error during parsing of the pom '{}'", file.getPath());
            LOGGER.debug("", ex);
            throw new AnalysisException(ex);
        }
    }