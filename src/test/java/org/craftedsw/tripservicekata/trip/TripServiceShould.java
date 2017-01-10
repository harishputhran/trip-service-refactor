package org.craftedsw.tripservicekata.trip;

import static org.craftedsw.tripservicekata.user.UserTest.UserBuilder.createUser;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceShould {

	private final User REGISTERED_USER = new User();
	private final Trip SHIMLA = new Trip();
	private final Trip ASSAM = new Trip();

	@Test(expected = UserNotLoggedInException.class)
	public void return_exception_when_user_is_not_logged_in() {
		User GUEST = null;
		TripService tripService = new TripServiceForTest();
		tripService.getTripsWithFriend(REGISTERED_USER, GUEST);
	}

	@Test
	public void return_no_trips_when_logged_user_is_not_friend() {
		TripService tripService = new TripServiceForTest();
		User unknownUser = createUser().addTrips(SHIMLA).addTrips(ASSAM).build();
		assertEquals(0, tripService.getTripsWithFriend(unknownUser, REGISTERED_USER).size());
	}

	@Test
	public void return_trips_when_logged_user_is_a_friend() {
		TripService tripService = new TripServiceForTest();
		User friend = createUser().addFriends(REGISTERED_USER).addTrips(SHIMLA).addTrips(ASSAM).build();
		assertEquals(1, tripService.getTripsWithFriend(friend, REGISTERED_USER).size());
	}

	private class TripServiceForTest extends TripService {		
		private TripDAO tripDAO = new TripDAO(){
			@Override
			public List<Trip> findTripsFor(User user){
				return user.trips();
			}
		};
		
		protected List<Trip> getTripsForUser(User user) {
			return tripDAO.findTripsFor(user);
		}		
	}
}
