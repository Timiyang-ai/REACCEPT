@Test
	public void writeStream() throws IOException
	{
		WriteCallback callback = new WriteCallback()
		{

			@Override
			public void writeData(Attributes attributes)
			{

			}
		};
		ByteArrayResponse response = new ByteArrayResponse();
		Attributes attributes = new Attributes(new MockWebRequest(new Url()), response);
		byte[] srcData = new byte[5000];
		for (int i = 0; i < srcData.length; i++)
		{
			srcData[i] = (byte)i;
		}
		InputStream in = new ByteArrayInputStream(srcData);
		callback.writeStream(attributes, in);
		assertTrue("Content not equal", Arrays.equals(response.getBytes(), srcData));
	}