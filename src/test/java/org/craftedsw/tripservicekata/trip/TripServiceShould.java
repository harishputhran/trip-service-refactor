package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceShould {

	private final User REGISTERED_USER = new User();
	private final Trip SHIMLA = new Trip();
	private final Trip ASSAM = new Trip();
	private User loggedInUser;

	@Test(expected = UserNotLoggedInException.class)
	public void return_exception_when_user_is_not_logged_in() {
		loggedInUser = null;
		User GUEST = null;
		TripService tripService = new TripServiceForTest();
		tripService.getTripsByUser(GUEST);
	}

	@Test
	public void return_no_trips_when_logged_user_is_not_friend() {
		TripService tripService = new TripServiceForTest();
		loggedInUser = REGISTERED_USER;
		User unknownUser = UserBuilder.createUser().addTrips(SHIMLA).addTrips(ASSAM).build();
		assertEquals(0, tripService.getTripsByUser(unknownUser).size());
	}

	@Test
	public void return_trips_when_logged_user_is_a_friend() {
		loggedInUser = REGISTERED_USER;
		TripService tripService = new TripServiceForTest();
		User friend = UserBuilder.createUser().addFriends(REGISTERED_USER).addTrips(SHIMLA).addTrips(ASSAM).build();
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

	public static class UserBuilder {

		public static UserBuilder createUser() {
			return new UserBuilder();
		}

		private List<User> friends = new ArrayList<>();
		private List<Trip> trips = new ArrayList<>();

		public UserBuilder addFriends(User... friends) {
			this.friends = asList(friends);
			return this;
		}

		public UserBuilder addTrips(Trip... trips) {
			this.trips = asList(trips);
			return this;
		}

		public User build() {
			User user = new User();
			for (Trip trip : trips) {
				user.addTrip(trip);
			}

			for (User friend : friends) {
				user.addFriend(friend);
			}
			return user;
		}
	}
}
