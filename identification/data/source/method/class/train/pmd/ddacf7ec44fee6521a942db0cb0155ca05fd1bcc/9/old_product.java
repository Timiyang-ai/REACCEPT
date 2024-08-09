public void tokenize (SourceCode sourceCode, Tokens tokenEntries )
	{
            long encounteredTokens = 0, addedTokens = 0;
            //Initialisation has to go here because the System properties are not set up when the Tokenizer is constructed 
	    if (!isInitialised) {
			setProperties(System.getProperties());
			isInitialised =  true;

			System.err.println("PLSQLTokenizer: ignoreComments=="+ignoreComments);
			System.err.println("PLSQLTokenizer: ignoreIdentifiers=="+ignoreIdentifiers);
			System.err.println("PLSQLTokenizer: ignoreLiterals=="+ignoreLiterals);
		}

		String fileName = sourceCode.getFileName();
		StringBuilder sb = sourceCode.getCodeBuffer();

		PLSQLParserTokenManager tokenMgr = new PLSQLParserTokenManager( new SimpleCharStream( new StringReader(sb.toString()))); 
		Token currentToken = tokenMgr.getNextToken();
		while (currentToken.image.length()  > 0)
		{
			String image = currentToken.image;

                        encounteredTokens++;
			if (ignoreComments && 
			    ( currentToken.kind == PLSQLParserConstants.SINGLE_LINE_COMMENT
			    ||currentToken.kind == PLSQLParserConstants.MULTI_LINE_COMMENT
			    ||currentToken.kind == PLSQLParserConstants.FORMAL_COMMENT
			    ||currentToken.kind == PLSQLParserConstants.COMMENT
			    ||currentToken.kind == PLSQLParserConstants.IN_MULTI_LINE_COMMENT
			    ||currentToken.kind == PLSQLParserConstants.IN_FORMAL_COMMENT
				)
				) {
				image = String.valueOf(currentToken.kind);
			}

			if (ignoreIdentifiers && 
			    (currentToken.kind == PLSQLParserConstants.IDENTIFIER
				)
				) {
				image = String.valueOf(currentToken.kind);
			}

			if (ignoreLiterals
				&& (   
					   currentToken.kind == PLSQLParserConstants.UNSIGNED_NUMERIC_LITERAL 
					|| currentToken.kind == PLSQLParserConstants.FLOAT_LITERAL
					|| currentToken.kind == PLSQLParserConstants.INTEGER_LITERAL
					|| currentToken.kind == PLSQLParserConstants.CHARACTER_LITERAL
				    || currentToken.kind == PLSQLParserConstants.STRING_LITERAL
					|| currentToken.kind == PLSQLParserConstants.QUOTED_LITERAL
					)
				) {
				image = String.valueOf(currentToken.kind);
			}

			tokenEntries.add(new TokenEntry(image, fileName, currentToken.beginLine));
                        addedTokens++;
			currentToken = tokenMgr.getNextToken();
		}
		tokenEntries.add(TokenEntry.getEOF() );
            LOGGER.fine(sourceCode.getFileName() 
                        + ": encountered " + encounteredTokens + " tokens;"
                        + " added " + addedTokens + " tokens"
                       );
	}