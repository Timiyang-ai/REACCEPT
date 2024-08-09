static Deque<Token> tokenize(final String value) {
		final Deque<Token> tokens = new ArrayDeque<>();

		char pc = '\0';
		int pos = 0;
		final StringBuilder token = new StringBuilder();
		for (int i = 0; i < value.length(); ++i) {
			final char c = value.charAt(i);

			if (isTokenSeparator(c) && pc != ESCAPE_CHAR) {
				tokens.add(new Token(unescape(token.toString()), pos));
				tokens.add(new Token(Character.toString(c), i));
				token.setLength(0);
				pos = i;
			} else {
				token.append(c);
			}

			pc = c;
		}

		if (token.length() > 0) {
			tokens.add(new Token(unescape(token.toString()), pos));
		}

		return tokens;
	}