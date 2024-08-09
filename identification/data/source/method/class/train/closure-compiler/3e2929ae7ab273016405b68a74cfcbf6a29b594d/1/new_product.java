public AstRoot parse(String sourceString, String sourceURI, int lineno)
    {
        if (parseFinished) throw new IllegalStateException("parser reused");
        this.sourceURI = sourceURI;
        if (compilerEnv.isIdeMode()) {
            this.sourceChars = sourceString.toCharArray();
        }
        this.ts = new TokenStream(this, null, sourceString, lineno);
        try {
            AstRoot r = parse();
            new AttachJsDocs().attachComments(r, jsdocs);
            return r;
        } catch (IOException iox) {
            // Should never happen
            throw new IllegalStateException();
        } finally {
            parseFinished = true;
        }
    }