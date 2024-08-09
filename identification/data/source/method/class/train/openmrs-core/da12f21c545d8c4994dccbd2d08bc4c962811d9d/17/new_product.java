public static Integer getConceptIdForUnits(String units) {
		String appDataDir = OpenmrsUtil.getApplicationDataDirectory();
		Properties props = new Properties();
		String conceptId = null;
		String filePath = appDataDir +
				System.getProperty("file.separator") +
				DatabaseUtil.ORDER_ENTRY_UPGRADE_SETTINGS_FILENAME;

		try (FileInputStream fis = new FileInputStream(filePath)) {

			props.load(fis);
			for (Map.Entry prop : props.entrySet()) {
				if (prop.getKey().equals(units)) {
					conceptId = prop.getValue().toString();
					return Integer.valueOf(conceptId);
				}
			}
		}
		catch (NumberFormatException e) {
			throw new APIException("Your order entry upgrade settings file" + "contains invalid mapping from " + units
			        + " to concept ID " + conceptId
			        + ". ID must be an integer or null. Please refer to upgrade instructions for more details. https://wiki.openmrs.org/x/OALpAw Cause:" + e.getMessage());
		}
		catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				throw new APIException("Unable to find file named order_entry_upgrade_settings.txt containing order entry upgrade settings in your "
				        + "application data directory: " + appDataDir
				        + "\nPlease refer to upgrade instructions for more details. https://wiki.openmrs.org/x/OALpAw Cause:" + e.getMessage());
			} else {
				throw new APIException(e);
			}
		}
		
		throw new APIException("Your order entry upgrade settings file" + " does not have mapping for " + units
		        + ". Please refer to upgrade instructions for more details. https://wiki.openmrs.org/x/OALpAw");
	}