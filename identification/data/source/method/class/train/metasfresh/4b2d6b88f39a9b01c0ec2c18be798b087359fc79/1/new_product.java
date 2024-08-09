public String buildAddressString(final I_C_Location location, boolean isLocalAddress, String bPartnerBlock, String userBlock)
	{
		final String displaySequence = getDisplaySequence(location.getC_Country(), isLocalAddress);

		String inStr = displaySequence;
		final StringBuilder outStr = new StringBuilder();

		final List<String> bracketsTxt = extractBracketsString(inStr);

		// treat brackets cases first if exists
		for (String s : bracketsTxt)
		{
			Check.assume(s.startsWith("(") || s.startsWith("\\("), "Expected brackets or escaped brackets!");
			Check.assume(s.endsWith(")") || s.endsWith("\\)"), "Expected brackets or escaped brackets!");

			String in = new String(s);
			final StringBuilder out = new StringBuilder();
			if (s.startsWith("("))
			{
				in = in.substring(1, s.length() - 1); // take out brackets
				replaceAddrToken(location, isLocalAddress, in, out, bPartnerBlock, userBlock, true);
			}
			else if (s.startsWith("\\("))
			{
				in = in.substring(1, in.length() - 2).concat(")"); // take out escaped chars
				replaceAddrToken(location, isLocalAddress, in, out, bPartnerBlock, userBlock, false);
			}

			// take the plus space
			if (out.length() == 0)
			{
				inStr = inStr.replace(s.concat(" "), out.toString());
			}
			else
			{
				if (out.lastIndexOf("\n") == out.length() - 1)
				{
					inStr = inStr.replace(s + " ", out.toString());
				}
				else
				{
					inStr = inStr.replace(s, out.toString());
				}
			}
		}

		// old behavior
		// variables in brackets already parsed
		replaceAddrToken(location, isLocalAddress, inStr, outStr, bPartnerBlock, userBlock, false);

		String retValue = Util.replace(outStr.toString().trim(), "\\n", "\n");
		return retValue;
	}