public OBODoc parse(String fn) throws IOException {
        if (fn.startsWith("http:")) {
            try (InputStream in = DocumentSources.getInputStream(IRI.create(fn), new OWLOntologyLoaderConfiguration())
                .get()) {
                return parse(in);
            } catch (OWLOntologyInputSourceException e) {
                throw (IOException) e.getCause();
            }
        }
        return parse(new File(fn));
    }