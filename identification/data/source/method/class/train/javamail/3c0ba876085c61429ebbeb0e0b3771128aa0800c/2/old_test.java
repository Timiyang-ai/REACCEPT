    public static void parse(BufferedReader in) throws IOException {
	String header = "";
	boolean doStrict = strict;
	boolean doParseHeader = parse_header;

	for (;;) {
	    String s = in.readLine();
	    if (s != null && s.length() > 0) {
		char c = s.charAt(0);
		if (c == ' ' || c == '\t') {
		    // a continuation line, add it to the current header
		    header += '\n' + s;
		    continue;
		}
	    }
	    // "s" is the next header, "header" is the last complete header
	    if (header.startsWith("Strict: ")) {
		doStrict = Boolean.parseBoolean(value(header));
	    } else if (header.startsWith("Header: ")) {
		doParseHeader = Boolean.parseBoolean(value(header));
	    } else if (header.startsWith("From: ") ||
		    header.startsWith("To: ") ||
		    header.startsWith("Cc: ")) {
		int i;
		String[] expect = null;
		if (s != null && s.startsWith("Expect: ")) {
		    try {
			int nexpect = Integer.parseInt(s.substring(8));
			expect = new String[nexpect];
			for (i = 0; i < nexpect; i++)
			    expect[i] = readLine(in).trim();
		    } catch (NumberFormatException e) {
			try {
			    if (s.substring(8, 17).equals("Exception")) {
				expect = new String[1];
				expect[0] = "Exception";
			    }
			} catch (StringIndexOutOfBoundsException se) {
			    // ignore it
			}
		    }
		}
		i = header.indexOf(':');
		try {
		    if (junit)
			testData.add(new Object[] {
			    header.substring(0, i), header.substring(i + 2),
			    expect, doStrict, doParseHeader });
		    else
			test(header.substring(0, i), header.substring(i + 2),
			    expect, doStrict, doParseHeader);
		} catch (StringIndexOutOfBoundsException e) {
		    e.printStackTrace(System.out);
		}
	    }
	    if (s == null)
		return;		// EOF
	    if (s.length() == 0) {
		while ((s = in.readLine()) != null) {
		    if (s.startsWith("From "))
			break;
		}
		if (s == null)
		    return;
	    }
	    header = s;
	}
    }