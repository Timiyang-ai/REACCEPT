public void cancelStackTraceSample(int sampleId, Throwable cause) {
		synchronized (lock) {
			if (isShutDown) {
				return;
			}

			PendingStackTraceSample sample = pendingSamples.remove(sampleId);
			if (sample != null) {
				if (cause != null) {
					LOG.info("Cancelling sample " + sampleId, cause);
				} else {
					LOG.info("Cancelling sample {}", sampleId);
				}

				sample.discard(cause);
				rememberRecentSampleId(sampleId);
			}
		}
	}