public Response addFeatureLayer(
			final String workspaceName,
			final String datastoreName,
			final String layerName,
			final String defaultStyle ) {
		if (defaultStyle != null) {
			getWebTarget().path(
					"rest/layers/" + layerName + ".json").request().put(
					Entity.entity(
							"{'layer':{'defaultStyle':{'name':'" + defaultStyle + "'}}}",
							MediaType.APPLICATION_JSON));
		}

		return getWebTarget().path(
				"rest/workspaces/" + workspaceName + "/datastores/" + datastoreName + "/featuretypes").request().post(
				Entity.entity(
						"{'featureType':{'name':'" + layerName + "'}}",
						MediaType.APPLICATION_JSON));
	}