public AstRoot parse(Reader sourceReader, String sourceURI, int lineno)
        throws IOException
    {
        if (parseFinished) throw new IllegalStateException("parser reused");
        if (compilerEnv.isIdeMode()) {
            return parse(readFully(sourceReader), sourceURI, lineno);
        }
        try {
            this.sourceURI = sourceURI;
            ts = new TokenStream(this, sourceReader, null, lineno);
            AstRoot r = parse();
            new AttachJsDocs().attachComments(r, jsdocs);
            return r;
        } finally {
            parseFinished = true;
        }
    }