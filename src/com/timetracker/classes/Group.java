package com.timetracker.classes;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.timetracker.interfaces.IDataRenderer;
import com.timetracker.interfaces.TaskRenderer;

public class Group {
	private String groupName;
	private final ArrayList<TaskBean> tasks;
	private IDataRenderer renderer;
	private final boolean isDefault;
	
	public Group(String name) {
		groupName = name;
		tasks = new ArrayList<TaskBean>();
		renderer = new TaskRenderer();
		isDefault = false;
	}
	
	public Group(String name, boolean isDefault) {
		groupName = name;
		tasks = new ArrayList<TaskBean>();
		renderer = new TaskRenderer();
		this.isDefault = isDefault;
	}
	
	public void addTask(TaskBean task) {
		if (!isDefault) {
			String taskName = task.getTaskDescription();
			task.setTaskDescription(taskName.replace(getName() + ":", ""));
		}
		tasks.add(task);
	}
	
	public void clear() {
		tasks.clear();
	}
	
	public String getName() {
		return groupName;
	}
	
	public long getDuration() {
		long time = 0;
		for (TaskBean task: tasks) {
			time += task.getDuration();
		}
		return time;
	}
	
	public ArrayList<TaskBean> getTasks() {
		return tasks;
	}
	
	public JSONObject renderTasks() {
		JSONObject json = renderer.render(tasks);
		try {
			json.put("group", getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}