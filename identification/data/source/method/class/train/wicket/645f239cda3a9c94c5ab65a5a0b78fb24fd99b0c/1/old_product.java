public boolean hasChildTag(String tagName)
	{
		boolean hasChild = false;

		if (Strings.isEmpty(tagName))
		{
			throw new IllegalArgumentException("You need to provide a not empty/not null argument.");
		}

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

					XmlTag tag = null;
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
				// NOTE: IllegalStateException(Throwable) only exists since Java 1.5
				throw new WicketRuntimeException(e);
			}

		}

		return hasChild;
	}