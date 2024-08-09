protected void clearResultSetHandles(boolean internalClose) throws SQLException {
		if (!internalClose){
			this.resultSetHandles.clear();
		} else {
			ResultSet rs = null;
			while ((rs=this.resultSetHandles.poll()) != null) {
				rs.close();
			}
		}
	}