package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
	
	private static final User REGISTERED_USER = new User();
	private static final Trip SHIMLA = new Trip();
	private User loggedInUser;

	@Test(expected=UserNotLoggedInException.class)
	public void should_return_exception_when_user_is_not_logged_in(){
		loggedInUser = null;
		User GUEST = null;
		TripService tripService = new TripServiceForTest();
		tripService.getTripsByUser(GUEST);		
	}
	
	@Test
	public void should_return_no_trips_when_logged_user_is_not_friend(){
		TripService tripService = new TripServiceForTest();
		loggedInUser = REGISTERED_USER;
		User unknownUser = new User();
		unknownUser.addTrip(SHIMLA);
		assertEquals(0, tripService.getTripsByUser(unknownUser).size());		
	}
	
	@Test
	public void should_return_trips_when_logged_user_is_a_friend(){
		loggedInUser = REGISTERED_USER;
		TripService tripService = new TripServiceForTest();		
		User friend = new User();
		friend.addFriend(REGISTERED_USER);
		friend.addTrip(SHIMLA);
		assertEquals(1, tripService.getTripsByUser(friend).size());		
	}
	
	
	 private class TripServiceForTest extends TripService {
		 @Override
		 protected User getLoggedInUser() {
			return loggedInUser;
		}
		 
		 protected List<Trip> getTripsForUser(User user) {
			return user.trips();
		}
	}
}
