@Override
    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) {
        long encounteredTokens = 0;
        long addedTokens = 0;

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("PLSQLTokenizer: ignoreIdentifiers==" + ignoreIdentifiers);
            LOGGER.fine("PLSQLTokenizer: ignoreLiterals==" + ignoreLiterals);
        }

        String fileName = sourceCode.getFileName();
        StringBuilder sb = sourceCode.getCodeBuffer();

        TokenFilter tokenFilter = new JavaCCTokenFilter(new PLSQLTokenManager(new StringReader(sb.toString())));
        Token currentToken = (Token) tokenFilter.getNextToken();
        while (currentToken != null) {
            String image = currentToken.image;

            encounteredTokens++;

            if (ignoreIdentifiers && currentToken.kind == PLSQLParserConstants.IDENTIFIER) {
                image = String.valueOf(currentToken.kind);
            }

            if (ignoreLiterals && (currentToken.kind == PLSQLParserConstants.UNSIGNED_NUMERIC_LITERAL
                    || currentToken.kind == PLSQLParserConstants.FLOAT_LITERAL
                    || currentToken.kind == PLSQLParserConstants.INTEGER_LITERAL
                    || currentToken.kind == PLSQLParserConstants.CHARACTER_LITERAL
                    || currentToken.kind == PLSQLParserConstants.STRING_LITERAL
                    || currentToken.kind == PLSQLParserConstants.QUOTED_LITERAL)) {
                image = String.valueOf(currentToken.kind);
            }

            tokenEntries.add(new TokenEntry(image, fileName, currentToken.beginLine));
            addedTokens++;
            currentToken = (Token) tokenFilter.getNextToken();
        }
        tokenEntries.add(TokenEntry.getEOF());
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine(sourceCode.getFileName() + ": encountered " + encounteredTokens + " tokens;" + " added "
                    + addedTokens + " tokens");
        }
    }