public static Number getMinValue(Class<? extends Number> numberType)
	{
		Number result;
		if (Integer.class == numberType || int.class == numberType)
		{
			result = Integer.MIN_VALUE;
		}
		else if (Long.class == numberType || long.class == numberType)
		{
			result = Long.MIN_VALUE;
		}
		else if (Float.class == numberType || float.class == numberType)
		{
			result = -Float.MAX_VALUE;
		}
		else if (Double.class == numberType || double.class == numberType)
		{
			result = -Double.MAX_VALUE;
		}
		else if (Byte.class == numberType || byte.class == numberType)
		{
			result = Byte.MIN_VALUE;
		}
		else if (Short.class == numberType || short.class == numberType)
		{
			result = Short.MIN_VALUE;
		}
		else
		{ // null of any other Number
			LOG.debug("'{}' has no minimum value. Falling back to Double.", numberType);
			result = -Double.MAX_VALUE;
		}

		return result;
	}