@Override
        public String getId() {
            if (groupID.equals(GROUP_TZDB)) {
                return regionID;
            }
            return groupID + ':' + regionID;
        }