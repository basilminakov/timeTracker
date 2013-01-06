package com.timetracker.core;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.timetracker.classes.Group;
import com.timetracker.classes.TaskBean;

public final class Core {

	private final HashMap<String, Group> groups = new HashMap<String, Group>();
	private final String DEFAULT_GROUP = "General";
	
	public Core() {	
		addGroup(new Group(DEFAULT_GROUP, true));
	}
	
	public static Pattern getGroupMask() {
		return Pattern.compile("^[A-Za-zР-пр-џ0-9_]+",  Pattern.UNICODE_CHARACTER_CLASS);
	}
	
	public static String getGroupName(String taskName) {
		if (taskName == null) {
			return null;
		}
		String[] parts = taskName.split("[:]");
		if (parts.length > 1 && parts[0].matches(getGroupMask().toString())) {
			return parts[0];
		}
		return null;
	}
	
	private Group getGroupByName(String groupName) {
		if (groupName != null) {
			return getGroups().get(groupName);
		}
		return null;
	}
	
	private void addGroup(Group group) {
		try {
			if (getGroups().containsKey(group.getName())) {
				throw new Exception(String.format("Group with name {0} already registered into Core!", group.getName()));
			}
			getGroups().put(group.getName(), group);
		} 
		catch (Exception e) {
			
		}
	}
	
	public HashMap<String, Group> getGroups() {
		return groups;
	}
	
	public void addTask(TaskBean task) {
		Group group;
		String groupName = Core.getGroupName(task.getTaskDescription());
		if (groupName == null) {
			group = getGroups().get(DEFAULT_GROUP);
		} else {
			group = getGroupByName(groupName);
			if (group == null) {
				group = new Group(groupName);
				addGroup(group);
			}
		}
		group.addTask(task);
	}
	
	public void addGroup(String groupName) {
		Group group = new Group(groupName);
		addGroup(group);
	}
}
