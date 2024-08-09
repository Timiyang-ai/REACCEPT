public double getOutput(Connection[] inputConnections) {

                if (inputConnections.length == 0) return 0d;

		boolean output = true;
		
		for (Connection connection : inputConnections) {
			output = output && (connection.getInput() >= 0.5d);
		}

		return output ? 1d : 0d;
	}