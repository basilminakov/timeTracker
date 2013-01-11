package com.timetracker.classes;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
		JSONArray tasksData = new JSONArray(); //renderer.render(tasks);
		JSONObject json = new JSONObject();
		json.put("tasks", tasksData);
		json.put("group", getName());
		return json;
	}
}