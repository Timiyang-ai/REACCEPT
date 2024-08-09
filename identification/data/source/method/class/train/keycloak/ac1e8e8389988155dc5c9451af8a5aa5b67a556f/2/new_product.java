public String getJsonProperty(JsonNode jsonNode, String name) {
        if (jsonNode.has(name) && !jsonNode.get(name).isNull()) {
        	  String s = jsonNode.get(name).asText();
        	  if(s != null && !s.isEmpty())
        	  		return s;
        	  else
      	  			return null;
        }

        return null;
    }