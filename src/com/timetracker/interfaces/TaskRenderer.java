package com.timetracker.interfaces;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
		
		DateTime time = null;
		for (TaskBean t : tasks) {
			time = new DateTime(t.getDuration());
			json.put(new JSONObject()
				.put("name", t.getTaskDescription())
				.put("time", time.toString(DateTimeFormat.shortTime().withZoneUTC()))
			);
		}
		return json;
	}
}
