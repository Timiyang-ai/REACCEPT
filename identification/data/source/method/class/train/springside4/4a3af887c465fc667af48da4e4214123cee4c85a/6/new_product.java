@Override
	public GetTeamDetailResult getTeamDetail(Long id) {
		GetTeamDetailResult result = new GetTeamDetailResult();
		try {

			Validate.notNull(id, "id参数为空");

			Team team = accountService.getTeamWithDetail(id);

			Validate.notNull(team, "项目不存在(id:" + id + ")");

			TeamDTO dto = BeanMapper.map(team, TeamDTO.class);
			result.setTeam(dto);

			return result;
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
	}