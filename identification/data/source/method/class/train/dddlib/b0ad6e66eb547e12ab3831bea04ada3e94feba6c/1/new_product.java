public boolean testConnection(Long id) {
		DataSourceVO dataSourceVO = this.getDataSourceVoById(id);
		// 将domain转成VO
		try {
			DataSource dataSource = new DataSource();
			BeanUtils.copyProperties(dataSource, dataSourceVO);
			return dataSource.testConnection();
		} catch (Exception e) {
			return false;
		}
	}