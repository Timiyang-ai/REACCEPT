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
            return parse();
        } finally {
            parseFinished = true;
        }
    }