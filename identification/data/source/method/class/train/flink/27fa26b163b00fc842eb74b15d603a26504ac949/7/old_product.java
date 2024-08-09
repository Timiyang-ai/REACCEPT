public void writeRecord(T record) throws IOException, InterruptedException {

		if (this.executingThread == null) {
			this.executingThread = Thread.currentThread();
		}

		if (this.executingThread.isInterrupted()) {
			throw new InterruptedException();
		}

		final int numberOfOutputChannels = this.outputChannels.size();
		final int[] selectedOutputChannels = this.channelSelector.selectChannels(record, numberOfOutputChannels);

		if (selectedOutputChannels == null) {
			return;
		}

		for (int i = 0; i < selectedOutputChannels.length; ++i) {

			if (selectedOutputChannels[i] < numberOfOutputChannels) {
				final AbstractOutputChannel<T> outputChannel = this.outputChannels.get(selectedOutputChannels[i]);
				outputChannel.writeRecord(record);
			}
		}
	}