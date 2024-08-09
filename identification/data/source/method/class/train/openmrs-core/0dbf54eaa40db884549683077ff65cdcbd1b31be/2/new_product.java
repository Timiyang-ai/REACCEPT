@Override
	public void setAsText(String asString) throws IllegalArgumentException {
		if (StringUtils.isEmpty(asString)) {
			setValue(null);
			return;
		}
		try {
			// first try date+time
			setValue(Context.getDateTimeFormat().parse(asString));
		}
		catch (ParseException dateTimeEx) {
			// next try just date
			try {
				setValue(Context.getDateFormat().parse(asString));
			}
			catch (ParseException dateEx) {
				// those were the only two options, so we fail
				throw new IllegalArgumentException(dateTimeEx);
			}
		}
	}