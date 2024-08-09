@Test
	public void renderAjaxAttributes()
	{
		AjaxRequestAttributes attributes = new AjaxRequestAttributes();
		attributes.getExtraParameters().put("param1", 123);
		attributes.getExtraParameters().put("param2", Locale.CANADA_FRENCH);

		AjaxCallListener listener = new AjaxCallListener();
		listener.onPrecondition("return somePrecondition();");
		listener.onBefore("alert('Before!');");
		listener.onAfter("alert('After!');");
		listener.onSuccess("alert('Success!');");
		listener.onFailure("alert('Failure!');");
		listener.onComplete("alert('Complete!');");
		attributes.getAjaxCallListeners().add(listener);

		Component component = Mockito.mock(Component.class);
		AbstractDefaultAjaxBehavior behavior = new AbstractDefaultAjaxBehavior()
		{
			@Override
			protected void respond(AjaxRequestTarget target)
			{
			}

			@Override
			public CharSequence getCallbackUrl()
			{
				return "some/url";
			}
		};
		behavior.bind(component);

		CharSequence json = behavior.renderAjaxAttributes(component, attributes);

		String expected = "{\"" + AjaxAttributeName.URL + "\":\"some/url\",\"" +
			AjaxAttributeName.BEFORE_HANDLER + "\":[function(attrs){alert('Before!');}],\"" +
			AjaxAttributeName.AFTER_HANDLER + "\":[function(attrs){alert('After!');}],\"" +
			AjaxAttributeName.SUCCESS_HANDLER +
			"\":[function(attrs, jqXHR, data, textStatus){alert('Success!');}],\"" +
			AjaxAttributeName.FAILURE_HANDLER +
			"\":[function(attrs, jqXHR, errorMessage, textStatus){alert('Failure!');}],\"" +
			AjaxAttributeName.COMPLETE_HANDLER +
			"\":[function(attrs, jqXHR, textStatus){alert('Complete!');}],\"" +
			AjaxAttributeName.PRECONDITION + "\":[function(attrs){return somePrecondition();}],\"" +
			AjaxAttributeName.EXTRA_PARAMETERS +
			"\":[{\"name\":\"param1\",\"value\":123},{\"name\":\"param2\",\"value\":\"fr_CA\"}]}";
		assertEquals(expected, json);
	}