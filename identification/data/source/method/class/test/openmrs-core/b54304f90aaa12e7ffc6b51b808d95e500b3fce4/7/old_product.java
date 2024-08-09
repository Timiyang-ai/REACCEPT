@Override
	public String getAsText() {
		Date date = (Date) getValue();
		if (date == null)
			return "";
		if ("0000".equals(new SimpleDateFormat("HmsS").format(date))) {
			return Context.getDateFormat().format(date);
		} else {
			return Context.getDateTimeFormat().format(date);
		}
	}