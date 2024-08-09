public Response addCoverage(
			final String workspaceName,
			final String cvgStoreName,
			final String coverageName ) {
		final String jsonString = "{'coverage':" + "{'name':'" + coverageName + "'," + "'nativeCoverageName':'"
				+ coverageName + "'}}";
		LOGGER.debug("Posting JSON: " + jsonString + " to " + workspaceName + "/" + cvgStoreName);

		return getWebTarget().path(
				"rest/workspaces/" + workspaceName + "/coveragestores/" + cvgStoreName + "/coverages").request().post(
				Entity.entity(
						jsonString,
						MediaType.APPLICATION_JSON));
	}