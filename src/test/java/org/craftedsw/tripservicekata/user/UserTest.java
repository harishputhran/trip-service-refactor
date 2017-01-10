package org.craftedsw.tripservicekata.user;

import static java.util.Arrays.asList;
import static org.craftedsw.tripservicekata.user.UserTest.UserBuilder.createUser;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Test;

public class UserTest {
	
	private final User LOGGED_IN_USER = new User();

	@Test
	public void indicate_if_user_is_not_friend(){
		User user = createUser().build();
		assertFalse(user.isFriendsWith(LOGGED_IN_USER));
	}
	
	@Test
	public void indicate_if_user_is_friend(){
		User user = createUser().addFriends(LOGGED_IN_USER)
							    .build();
		assertTrue(user.isFriendsWith(LOGGED_IN_USER));
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
