public boolean analyzeManifestContents(InputStream inputStream) throws IOException {
		char[] chars = Util.getInputStreamAsCharArray(inputStream, -1, Util.UTF_8);
		int state = START, substate = 0;
		StringBuffer currentJarToken = new StringBuffer();
		int currentChar;
		this.classpathSectionsCount = 0;
		this.calledFilesNames = null;
		for (int i = 0, max = chars.length; i < max;) {
			currentChar = chars[i++];
			if (currentChar == '\r') {
				// skip \r, will consider \n later (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=251079 )
				if (i < max) {
					currentChar = chars[i++];
				}
			}
			switch (state) {
				case START:
					if (currentChar == CLASSPATH_HEADER_TOKEN[0]) {
						state = IN_CLASSPATH_HEADER;
						substate = 1;
					} else {
						state = SKIP_LINE;
					}
					break;
				case IN_CLASSPATH_HEADER:
					if (currentChar == '\n') {
						state = START;
					} else if (currentChar != CLASSPATH_HEADER_TOKEN[substate++]) {
						state = SKIP_LINE;
					} else if (substate == CLASSPATH_HEADER_TOKEN.length) {
						state = PAST_CLASSPATH_HEADER;
					}
					break;
				case PAST_CLASSPATH_HEADER:
					if (currentChar == ' ') {
						state = SKIPPING_WHITESPACE;
						this.classpathSectionsCount++;
					} else {
						return false;
					}
					break;
				case SKIPPING_WHITESPACE:
					if (currentChar == '\n') {
						state = CONTINUING;
					} else if (currentChar != ' ') {
						currentJarToken.append((char) currentChar);
						state = READING_JAR;
					} else {
						// >>>>>>>>>>>>>>>>>> Add the latest jar read
						addCurrentTokenJarWhenNecessary(currentJarToken);
					}
					break;
				case CONTINUING:
					if (currentChar == '\n') {
						addCurrentTokenJarWhenNecessary(currentJarToken);
						state = START;
					} else if (currentChar == ' ') {
						state = SKIPPING_WHITESPACE;
					} else if (currentChar == CLASSPATH_HEADER_TOKEN[0]) {
						addCurrentTokenJarWhenNecessary(currentJarToken);
						state = IN_CLASSPATH_HEADER;
						substate = 1;
					} else if (this.calledFilesNames == null) {
						// >>>>>>>>>>>>>>>>>> Add the latest jar read
						addCurrentTokenJarWhenNecessary(currentJarToken);
						state = START;
					} else {
						// >>>>>>>>>>>>>>>>>> Add the latest jar read
						addCurrentTokenJarWhenNecessary(currentJarToken);
						state = SKIP_LINE;
					}
					break;
				case SKIP_LINE:
					if (currentChar == '\n') {
						state = START;
					}
					break;
				case READING_JAR:
					if (currentChar == '\n') {
						// appends token below
						state = CONTINUING;
						// >>>>>>>>>>> Add a break to not add the jar yet as it can continue on the next line
						break;
					} else if (currentChar == ' ') {
						// appends token below
						state = SKIPPING_WHITESPACE;
					} else {
						currentJarToken.append((char) currentChar);
						break;
					}
					addCurrentTokenJarWhenNecessary(currentJarToken);
					break;
			}
		}
		switch (state) {
			case START:
				return true;
			case IN_CLASSPATH_HEADER:
				return true;
			case PAST_CLASSPATH_HEADER:
				return false;
			case SKIPPING_WHITESPACE:
				// >>>>>>>>>>>>>>>>>> Add the latest jar read
				addCurrentTokenJarWhenNecessary(currentJarToken);
				return true;
			case CONTINUING:
				// >>>>>>>>>>>>>>>>>> Add the latest jar read
				addCurrentTokenJarWhenNecessary(currentJarToken);
				return true;
			case SKIP_LINE:
				if (this.classpathSectionsCount != 0) {
					if (this.calledFilesNames == null) {
						return false;
					}
				}
				return true;
			case READING_JAR:
				// >>>>>>>>>>>>>>>>>> Add the latest jar read
				return false;
		}
		return true;
	}