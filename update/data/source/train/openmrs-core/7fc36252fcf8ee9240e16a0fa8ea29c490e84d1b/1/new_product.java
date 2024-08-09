@Override
	public String serialize(Object o) throws SerializationException {
		if (o == null)
			return null;
		if (o instanceof LocalizedString) {
			LocalizedString localizedString = (LocalizedString) o;
			StringBuffer sb = new StringBuffer("");
			sb.append(LocalizedStringUtil.escapeDelimiter(localizedString.getUnlocalizedValue()));
			if (localizedString.getVariants() != null && !localizedString.getVariants().isEmpty()) {
				sb.insert(0, HEADER);
				sb.insert(HEADER.length(), "unlocalized:");
				sb.append(";");
				Iterator<Entry<Locale, String>> it = localizedString.getVariants().entrySet().iterator();
				while (it.hasNext()) {
					Entry<Locale, String> entry = it.next();
					sb.append(entry.getKey());
					sb.append(PARTITION);
					sb.append(LocalizedStringUtil.escapeDelimiter(entry.getValue()));
					sb.append(SPLITTER);
				}
			}
			return sb.toString();
		} else {
			throw new SerializationException("Can not serialize an object of type:" + o.getClass().getName());
		}
	}