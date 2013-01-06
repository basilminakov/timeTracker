package com.timetracker.interfaces;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import com.timetracker.classes.TaskBean;

public class TaskRenderer implements IDataRenderer {

	private ArrayList<TaskBean> tasks;
	
	@SuppressWarnings("unchecked")
	public JSONArray render(Object data) {
		JSONArray json = new JSONArray();
		if (data != null) {
			tasks = (ArrayList<TaskBean>)data;
		} else {
			return json;
		}
		try {
			DateTime time = null;
			for (TaskBean t : tasks) {
				time = new DateTime(t.getDuration());
				json.put(new JSONObject()
					.put("name", t.getTaskDescription())
					.put("time", time.toString(DateTimeFormat.shortTime().withZoneUTC()))
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
