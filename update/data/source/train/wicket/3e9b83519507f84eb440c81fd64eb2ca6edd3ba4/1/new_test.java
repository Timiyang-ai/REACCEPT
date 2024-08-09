@Test
	public void renderAjaxAttributes()
	{
		AjaxRequestAttributes attributes = new AjaxRequestAttributes();

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

		String expected = "{\"" + AjaxAttributeName.COMPLETE_HANDLER.jsonName() +
			"\":[function(attrs, jqXHR, textStatus){alert('Complete!');}],\"" +
			AjaxAttributeName.URL.jsonName() + "\":\"some/url\",\"" +
			AjaxAttributeName.PRECONDITION.jsonName() +
			"\":[function(attrs){return somePrecondition();}],\"" +
			AjaxAttributeName.FAILURE_HANDLER.jsonName() +
			"\":[function(attrs, jqXHR, errorMessage, textStatus){alert('Failure!');}],\"" +
			AjaxAttributeName.BEFORE_HANDLER.jsonName() +
			"\":[function(attrs){alert('Before!');}],\"" +
			AjaxAttributeName.SUCCESS_HANDLER.jsonName() +
			"\":[function(attrs, jqXHR, data, textStatus){alert('Success!');}],\"" +
			AjaxAttributeName.AFTER_HANDLER.jsonName() + "\":[function(attrs){alert('After!');}]}";
		assertEquals(expected, json);
	}