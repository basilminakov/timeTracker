package com.timetracker.interfaces;

import java.util.ArrayList;

import org.json.JSONObject;

import com.timetracker.classes.TaskBean;

public class TaskRenderer implements IDataRenderer {

	private ArrayList<TaskBean> tasks;
	
	@SuppressWarnings("unchecked")
	public JSONObject render(Object data) {
		JSONObject json = new JSONObject();
		if (data != null) {
			tasks = (ArrayList<TaskBean>)data;
		} else {
			return json;
		}
		for (TaskBean t : tasks) {
			
		}
		return json;
	}

}
