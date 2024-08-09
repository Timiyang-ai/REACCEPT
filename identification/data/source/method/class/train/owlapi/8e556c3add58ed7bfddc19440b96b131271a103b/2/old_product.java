public OBODoc parse(Reader reader) {
        setReader(new BufferedReader(reader));
        OBODoc obodoc = new OBODoc();
        parseOBODoc(obodoc);
        // handle imports
        Frame hf = obodoc.getHeaderFrame();
        List<OBODoc> imports = new LinkedList<>();
        if (hf != null) {
            for (Clause cl : hf.getClauses(OboFormatTag.TAG_IMPORT)) {
                String path = resolvePath(cl.getValue(String.class));
                // TBD -- changing the relative path to absolute
                cl.setValue(path);
                if (followImport) {
                    // resolve OboDoc documents from import paths.
                    OBOFormatParser parser = new OBOFormatParser();
                    OBODoc doc = parser.parseURL(path);
                    imports.add(doc);
                }
            }
            obodoc.setImportedOBODocs(imports);
        }
        return obodoc;
    }