public static Contract[] getInputs(final Contract contract) {
		if (contract instanceof DataSinkContract<?, ?>)
			return new Contract[] { ((DataSinkContract<?, ?>) contract).getInput() };
		if (contract instanceof SingleInputContract<?, ?, ?, ?>)
			return new Contract[] { ((SingleInputContract<?, ?, ?, ?>) contract).getInput() };
		if (contract instanceof DualInputContract<?, ?, ?, ?, ?, ?>)
			return new Contract[] { ((DualInputContract<?, ?, ?, ?, ?, ?>) contract).getFirstInput(),
				((DualInputContract<?, ?, ?, ?, ?, ?>) contract).getSecondInput() };
		return new Contract[0];
	}