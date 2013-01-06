package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import com.timetracker.classes.Group;
import com.timetracker.classes.TaskBean;
import com.timetracker.core.Core;

public class CoreTest {
	
	private TaskBean task1;
	private TaskBean task2;
	private Core core;
	
	public CoreTest() {
		core  = new Core();
		task1 = new TaskBean("gr1:Working hard");
		task2 = new TaskBean(DateTime.now(), "gr1:Working hard again", Duration.standardMinutes(3));
	}
	
	@Test
	public void test() {
		testGroupNameParsing();
		testTasksCalculation();
		testGroupCreation();
		testTimeCalculation();
	}
	
	public void testGroupNameParsing() {
		String enGroup = "gRoUp1:Illustrate test";
		String ruGroup = "гРупПа2:Подготовка к докладу";
		
		assertEquals("gRoUp1", Core.getGroupName(enGroup));
		assertEquals("гРупПа2", Core.getGroupName(ruGroup));
	}

	public void testTasksCalculation() {
		assertEquals(Duration.ZERO, task1.getTaskDuration());
		assertEquals(Duration.standardMinutes(3), task2.getTaskDuration());
		assertEquals(Duration.standardMinutes(3).getMillis(), task2.getDuration());
	}
	
	public void testGroupCreation() {
		assertEquals(1, core.getGroups().size());
		core.addTask(task1);
		assertEquals(2, core.getGroups().size());
		core.addTask(task2);
		assertEquals(2, core.getGroups().size());
		core.addTask(new TaskBean(""));
		assertEquals(2, core.getGroups().size());
		core.addTask(new TaskBean(""));
		assertEquals(2, core.getGroups().size());
		core.addTask(new TaskBean(DateTime.now(), null, Duration.standardHours(1)));
		assertEquals(2, core.getGroups().size());
	}
	
	public void testTimeCalculation() {
		long time = 0;
		HashMap<String, Group> groups = core.getGroups();
		for (Group group : groups.values()) {
			time += group.getDuration();
		}
		Duration dur = Duration.millis(time);
		assertEquals(1, dur.getStandardHours());
		assertEquals(63, dur.getStandardMinutes());
		DateTime nTime = new DateTime(time);
		assertEquals("1:03", nTime.toString(DateTimeFormat.shortTime().withZoneUTC()));
	}
}
