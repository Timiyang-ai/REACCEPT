	private String[] resolveParameterNames(final MethodParameter[] methodParameters) {
		final String[] result = new String[methodParameters.length];
		for (
				int i = 0, methodParametersLength = methodParameters.length;
				i < methodParametersLength; i++) {
			final MethodParameter methodParameter = methodParameters[i];

			result[i] = methodParameter.getName();
		}
		return result;
	}