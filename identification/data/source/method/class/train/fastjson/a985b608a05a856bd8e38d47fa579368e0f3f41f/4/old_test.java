	@SuppressWarnings("deprecation")
	public void test_read() throws Exception {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		converter.setCharset(Charset.forName("UTF-8"));
		Assert.assertEquals(Charset.forName("UTF-8"), converter.getCharset());

		converter.setFeatures(SerializerFeature.BrowserCompatible);
		Assert.assertEquals(1, converter.getFeatures().length);
		Assert.assertEquals(SerializerFeature.BrowserCompatible,
				converter.getFeatures()[0]);

		Assert.assertNull(converter.getDateFormat());
		converter.setDateFormat("yyyyMMdd");

		converter.setFilters(serializeFilter);
		Assert.assertEquals(1, converter.getFilters().length);
		Assert.assertEquals(serializeFilter, converter.getFilters()[0]);

		converter.addSerializeFilter(serializeFilter);
		Assert.assertEquals(2, converter.getFilters().length);
		converter.addSerializeFilter(null);

		converter.setSupportedMediaTypes(Arrays
				.asList(new MediaType[] { MediaType.APPLICATION_JSON_UTF8 }));
		Assert.assertEquals(1, converter.getSupportedMediaTypes().size());

		Method method = FastJsonHttpMessageConverter.class.getDeclaredMethod(
				"supports", Class.class);
		method.setAccessible(true);
		method.invoke(converter, int.class);

		HttpInputMessage input = new HttpInputMessage() {

			public HttpHeaders getHeaders() {
				// TODO Auto-generated method stub
				return null;
			}

			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("{\"id\":123}".getBytes(Charset
						.forName("UTF-8")));
			}

		};
		VO vo = (VO) converter.read(VO.class, input);
		Assert.assertEquals(123, vo.getId());

		final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		HttpOutputMessage out = new HttpOutputMessage() {

			public HttpHeaders getHeaders() {
				return new HttpHeaders();
			}

			public OutputStream getBody() throws IOException {
				return byteOut;
			}
		};
		converter.write(vo, MediaType.TEXT_PLAIN, out);

		byte[] bytes = byteOut.toByteArray();
		Assert.assertEquals("{\"id\":\"123\"}", new String(bytes, "UTF-8"));
	}