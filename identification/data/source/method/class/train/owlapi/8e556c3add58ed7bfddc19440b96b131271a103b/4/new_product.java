public OBODoc parse(Reader reader) throws IOException {
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
                } /*
                   * else{ //build a proxy document which reference import path
                   * as ontology id Frame importHeaer = new
                   * Frame(FrameType.HEADER); Clause ontologyCl = new Clause();
                   * ontologyCl.setTag(OboFormatTag.TAG_ONTOLOGY.getTag());
                   * ontologyCl.setValue(path);
                   * importHeaer.addClause(ontologyCl);
                   * doc.setHeaderFrame(importHeaer); }
                   */
            }
            obodoc.setImportedOBODocs(imports);
        }
        return obodoc;
    }