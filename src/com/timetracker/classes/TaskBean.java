package com.timetracker.classes;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TaskBean {
	private DateTime taskDate;
	private String taskDescripton;
	private Duration taskDuration;
	private final String DEFAULT_NAME = "Unnamed";
	
	public TaskBean(DateTime date, String description, Duration duration) {
		setTaskDate(date);
		setTaskDescription(description);
		setTaskDuration(duration);
	}
	
	public TaskBean(String description) {
		setTaskDescription(description);
		setTaskDate(DateTime.now());
		setTaskDuration(Duration.ZERO);
	}
	
	public DateTime getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(DateTime taskDate) {
		this.taskDate = taskDate;
	}

	public String getTaskDescription() {
		return taskDescripton;
	}

	public void setTaskDescription(String taskDescripton) {
		if (taskDescripton == null || "".equals(taskDescripton)) {
			taskDescripton = DEFAULT_NAME;
		}
		this.taskDescripton = taskDescripton;
	}

	public Duration getTaskDuration() {
		return taskDuration;
	}
	
	public long getDuration() {
		return taskDuration.getMillis();
	}

	public void setTaskDuration(Duration taskDuration) {
		this.taskDuration = taskDuration;
	}
}
