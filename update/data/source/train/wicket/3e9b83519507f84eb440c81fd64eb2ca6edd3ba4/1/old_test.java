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

		String expected = "{\"coh\":[function(attrs, jqXHR, textStatus){alert('Complete!');}],\"u\":\"some/url\",\"pre\":[function(attrs){return somePrecondition();}],\"fh\":[function(attrs, jqXHR, errorMessage, textStatus){alert('Failure!');}],\"bh\":[function(attrs){alert('Before!');}],\"sh\":[function(attrs, jqXHR, data, textStatus){alert('Success!');}],\"ah\":[function(attrs){alert('After!');}]}";
		assertEquals(expected, json);
	}