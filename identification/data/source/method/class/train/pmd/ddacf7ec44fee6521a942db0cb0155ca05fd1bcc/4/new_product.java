@Override
    protected TokenEntry processToken(Tokens tokenEntries, GenericToken currentToken, String fileName) {
        String image = currentToken.getImage();

        Token plsqlToken = (Token) currentToken;

        if (ignoreIdentifiers && plsqlToken.kind == PLSQLParserConstants.IDENTIFIER) {
            image = String.valueOf(plsqlToken.kind);
        }

        if (ignoreLiterals && (plsqlToken.kind == PLSQLParserConstants.UNSIGNED_NUMERIC_LITERAL
                || plsqlToken.kind == PLSQLParserConstants.FLOAT_LITERAL
                || plsqlToken.kind == PLSQLParserConstants.INTEGER_LITERAL
                || plsqlToken.kind == PLSQLParserConstants.CHARACTER_LITERAL
                || plsqlToken.kind == PLSQLParserConstants.STRING_LITERAL
                || plsqlToken.kind == PLSQLParserConstants.QUOTED_LITERAL)) {
            image = String.valueOf(plsqlToken.kind);
        }

        return new TokenEntry(image, fileName, currentToken.getBeginLine());
    }