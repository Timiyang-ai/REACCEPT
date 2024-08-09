@Nonnull
    public OBODoc parse(BufferedReader reader) throws IOException {
        setReader(reader);
        OBODoc obodoc = new OBODoc();
        parseOBODoc(obodoc);
        // handle imports
        Frame hf = obodoc.getHeaderFrame();
        List<OBODoc> imports = new LinkedList<>();
        if (hf != null) {
            for (Clause cl : hf.getClauses(OboFormatTag.TAG_IMPORT)) {
                @SuppressWarnings("null")
                String path = resolvePath(cl.getValue(String.class));
                // TBD -- changing the relative path to absolute
                cl.setValue(path);
                if (followImport) {
                    // resolve OboDoc documents from import paths.
                    OBODoc doc = importCache.get(path);
                    if (doc == null) {
                        OBOFormatParser parser = new OBOFormatParser(importCache);
                        doc = parser.parseURL(path);
                    }
                    imports.add(doc);
                }
            }
            obodoc.setImportedOBODocs(imports);
        }
        return obodoc;
    }