@Deprecated
	public static void writeLinkUrl(final Response response, final CharSequence url,
		final CharSequence media, final String markupId, final String rel)
	{
		AttributeMap attributes = new AttributeMap();
		attributes.putAttribute(ATTR_LINK_REL, Strings.isEmpty(rel) ? "stylesheet" : rel);
		attributes.putAttribute(ATTR_TYPE, "text/css");
		attributes.putAttribute(ATTR_LINK_HREF, url);
		attributes.putAttribute(ATTR_LINK_MEDIA, media.toString());
		attributes.putAttribute(ATTR_ID, markupId);
		writeLink(response, attributes);
	}