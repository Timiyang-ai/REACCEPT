public AbstractLink setBody(final IModel<?> bodyModel)
	{
		this.bodyModel = wrap(bodyModel);
		return this;
	}