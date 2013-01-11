package com.timetracker.interfaces;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.timetracker.classes.TaskBean;

public class TaskRenderer implements IDataRenderer {

	private ArrayList<TaskBean> tasks;
	
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
			JSONObject task = new JSONObject();
			json.add(task);
			task.put("name", t.getTaskDescription());
			task.put("time", time.toString(DateTimeFormat.shortTime().withZoneUTC()));
		}
		return json;
	}
}
