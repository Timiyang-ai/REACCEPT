void stop() {
		timer.cancel();
		for (final Collector collector : collectorsByApplication.values()) {
			collector.stop();
		}

		// nettoyage avant le retrait de la webapp au cas où celui-ci ne suffise pas
		collectorsByApplication.clear();
		javaInformationsByApplication.clear();
	}