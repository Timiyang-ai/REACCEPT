protected void closeAndClearResultSetHandles() throws SQLException {
		ResultSet rs = null;
		while ((rs=this.resultSetHandles.poll()) != null) {
			rs.close();
		}
	}