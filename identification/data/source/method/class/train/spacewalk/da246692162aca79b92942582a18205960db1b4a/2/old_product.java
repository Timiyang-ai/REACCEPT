public String buildPageLink(String name, String value) {
        StringBuffer page = new StringBuffer((String)request
                .getAttribute("requestedUri"));

        if (request.getQueryString() != null) {
            int index = request.getQueryString().indexOf(name + "=");
            // if we already have this param in the query string we have to
            // reset it to the new value
            if (index >= 0) {
                Map<String, String> parammap = new TreeMap<String, String>();
                String[] params = StringUtils.split(request.getQueryString(),
                '&');
                // Convert the parameters into a map so we can
                // easily replace the value and reformat the query string.
                for (int i = 0; i < params.length; i++) {
                    String[] nameval = StringUtils.split(params[i], '=');
                    parammap.put(nameval[0], nameval[1]);
                }
                parammap.remove(name);
                parammap.put(name, value);
                page.append("?");
                Iterator<String> i = parammap.keySet().iterator();
                while (i.hasNext()) {
                    String key = i.next();
                    page.append(key + "=" + parammap.get(key));
                    if (i.hasNext()) {
                        page.append("&");
                    }
                }
            }
            else {
                page.append("?" + name + "=" + value + "&" + request.getQueryString());
                return page.toString();
            }
        }
        else {
            page.append("?" + name + "=" + value);
        }
        return page.toString();
    }