@Override
	public GetTeamDetailResponse getTeamDetail(Long id) {
		GetTeamDetailResponse response = new GetTeamDetailResponse();
		try {

			Validate.notNull(id, "id参数为空");

			Team team = accountService.getTeamWithDetail(id);

			Validate.notNull(team, "项目不存在(id:" + id + ")");

			TeamDTO dto = BeanMapper.map(team, TeamDTO.class);
			response.setTeam(dto);

			return response;
		} catch (IllegalArgumentException e) {
			return handleParameterError(response, e);
		} catch (RuntimeException e) {
			return handleGeneralError(response, e);
		}
	}