public boolean hasChildTag(String tagName)
	{
		Args.notEmpty(tagName, "tagName");

		boolean hasChild = false;

		if (openTag.isOpen())
		{
			try
			{
				// Get the content of the tag
				int startPos = openTag.getPos() + openTag.getLength();
				int endPos = closeTag.getPos();
				String markup = parser.getInput(startPos, endPos).toString();

				if (Strings.isEmpty(markup) == false)
				{
					XmlPullParser p = new XmlPullParser();
					p.parse(markup);

					XmlTag tag;
					while ((tag = p.nextTag()) != null)
					{
						if (tagName.equalsIgnoreCase(tag.getName()))
						{
							hasChild = true;
							break;
						}
					}
				}
			}
			catch (Exception e)
			{
				throw new WicketRuntimeException(e);
			}
		}

		return hasChild;
	}