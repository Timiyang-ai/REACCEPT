public Response addFeatureLayer(
			final String workspaceName,
			final String datastoreName,
			final String layerName ) {
		return getWebTarget().path(
				"rest/workspaces/" + workspaceName + "/datastores/" + datastoreName + "/featuretypes").request().post(
				Entity.entity(
						"{'featureType':{'name':'" + layerName + "'}}",
						MediaType.APPLICATION_JSON));
	}