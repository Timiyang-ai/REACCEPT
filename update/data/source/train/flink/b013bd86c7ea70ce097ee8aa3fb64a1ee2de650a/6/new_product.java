public static void setInputs(final Contract contract, final Contract[] inputs) {
		if (contract instanceof DataSinkContract<?, ?>) {
			if (inputs.length != 1)
				throw new IllegalArgumentException("wrong number of inputs");
			((DataSinkContract<?, ?>) contract).setInput(inputs[0]);
		} else if (contract instanceof SingleInputContract<?, ?, ?, ?>) {
			if (inputs.length != 1)
				throw new IllegalArgumentException("wrong number of inputs");
			((SingleInputContract<?, ?, ?, ?>) contract).setInput(inputs[0]);
		} else if (contract instanceof DualInputContract<?, ?, ?, ?, ?, ?>) {
			if (inputs.length != 2)
				throw new IllegalArgumentException("wrong number of inputs");
			((DualInputContract<?, ?, ?, ?, ?, ?>) contract).setFirstInput(inputs[0]);
			((DualInputContract<?, ?, ?, ?, ?, ?>) contract).setSecondInput(inputs[1]);
		}
	}