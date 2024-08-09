public static Model readPom(String path, JarFile jar) throws AnalysisException {
        final ZipEntry entry = jar.getEntry(path);
        Model model = null;
        if (entry != null) { //should never be null
            //noinspection CaughtExceptionImmediatelyRethrown
            try {
                final PomParser parser = new PomParser();
                model = parser.parse(jar.getInputStream(entry));
                if (model == null) {
                    throw new AnalysisException(String.format("Unable to parse pom '%s/%s'", jar.getName(), path));
                }
            } catch (AnalysisException ex) {
                throw ex;
            } catch (SecurityException ex) {
                LOGGER.warn("Unable to parse pom '{}' in jar '{}'; invalid signature", path, jar.getName());
                LOGGER.debug("", ex);
                throw new AnalysisException(ex);
            } catch (IOException ex) {
                LOGGER.warn("Unable to parse pom '{}' in jar '{}' (IO Exception)", path, jar.getName());
                LOGGER.debug("", ex);
                throw new AnalysisException(ex);
            } catch (Throwable ex) {
                LOGGER.warn("Unexpected error during parsing of the pom '{}' in jar '{}'", path, jar.getName());
                LOGGER.debug("", ex);
                throw new AnalysisException(ex);
            }
        }
        return model;
    }