package com.timetracker.interfaces;

import net.sf.json.JSONArray;

public interface IDataRenderer {
	JSONArray render(Object data);
}
