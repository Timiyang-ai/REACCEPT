public static Model readPom(String path, JarFile jar) throws AnalysisException {
        final ZipEntry entry = jar.getEntry(path);
        final PomParser parser = new PomParser();
        Model model = null;
        if (entry != null) { //should never be null
            //noinspection CaughtExceptionImmediatelyRethrown
            try {
                model = parser.parse(jar.getInputStream(entry));
            } catch (PomParseException ex) {
                if (ex.getCause() instanceof SAXParseException) {
                    try {
                        model = parser.parseWithoutDocTypeCleanup(jar.getInputStream(entry));
                    } catch (PomParseException ex1) {
                        LOGGER.warn("Unable to parse pom '{}' in jar '{}'", path, jar.getName());
                        LOGGER.debug("", ex1);
                        throw new AnalysisException(ex1);
                    } catch (IOException ex1) {
                        LOGGER.warn("Unable to parse pom '{}' in jar '{}' (IO Exception)", path, jar.getName());
                        LOGGER.debug("", ex);
                        throw new AnalysisException(ex);
                    }
                }
                if (model != null) {
                    LOGGER.warn("Unable to parse pom '{}' in jar '{}'", path, jar.getName());
                    LOGGER.debug("", ex);
                    throw new AnalysisException(ex);
                }
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
            if (model == null) {
                throw new AnalysisException(String.format("Unable to parse pom '%s/%s'", jar.getName(), path));
            }
        }
        return model;
    }