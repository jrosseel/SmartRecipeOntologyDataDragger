package main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import service.SmartRecipeUpdate;

public class SmartRecipeDataDragger {

	// Kicks of the service that runs every day
	
	public static void main(String[] args) {
		//_kickOffService();
		new SmartRecipeUpdate().run();
	}
	
	/**Service that runs in the background and executes the recipe 
	 *   triplestore update every day.
	 * 
	 * @see: http://stackoverflow.com/questions/20387881/how-to-run-certain-task-every-day-at-a-particular-time-using-scheduledexecutorse */
	private static void _kickOffService() {
		LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("Europe/Brussels");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNext12;
        
        // Set the time the service needs to run.
        zonedNext12 = zonedNow.withHour(12).withMinute(0).withSecond(0);
        if(zonedNow.compareTo(zonedNext12) > 0)
            zonedNext12 = zonedNext12.plusDays(1);
        Duration duration = Duration.between(zonedNow, zonedNext12);
        long initalDelay = duration.getSeconds();

        // Schedule the service
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
        scheduler.scheduleAtFixedRate(new SmartRecipeUpdate(), initalDelay,
                                      24*60*60, TimeUnit.SECONDS);
	}
	
	
	
}
