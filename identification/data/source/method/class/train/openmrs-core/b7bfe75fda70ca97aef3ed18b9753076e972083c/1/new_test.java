	@Test
	public void serialize_shouldSerializeObject() throws SerializationException {
		
		OpenmrsSerializer serializer = new SimpleXStreamSerializer();
		
		Foo foo = new Foo("test", 1);
		List<String> list = new ArrayList<>();
		list.add("foo");
		list.add("bar");
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "foo");
		map.put(2, "fooBar");
		map.put(3, "bar");
		foo.setAttributeList(list);
		foo.setAttributeMap(map);
		
		String serializedFoo = serializer.serialize(foo);
		
		Assert.assertTrue(StringUtils.deleteWhitespace(serializedFoo).equals(
		    StringUtils.deleteWhitespace("<org.openmrs.serialization.Foo>\n" + "  <attributeString>test</attributeString>\n"
		            + "  <attributeInt>1</attributeInt>\n" + "  <attributeList>\n" + "    <string>foo</string>\n"
		            + "    <string>bar</string>\n" + "  </attributeList>\n" + "  <attributeMap>\n" + "    <entry>\n"
		            + "      <int>1</int>\n" + "      <string>foo</string>\n" + "    </entry>\n" + "    <entry>\n"
		            + "      <int>2</int>\n" + "      <string>fooBar</string>\n" + "    </entry>\n" + "    <entry>\n"
		            + "      <int>3</int>\n" + "      <string>bar</string>\n" + "    </entry>\n" + "  </attributeMap>\n"
		            + "  </org.openmrs.serialization.Foo>")));
		
	}