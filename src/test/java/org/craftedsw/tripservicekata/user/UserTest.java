package org.craftedsw.tripservicekata.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.craftedsw.tripservicekata.trip.TripServiceShould.UserBuilder;
import org.junit.Test;

public class UserTest {
	
	private final User LOGGED_IN_USER = new User();

	@Test
	public void indicate_if_user_is_not_friend(){
		User user = UserBuilder.createUser().build();
		assertFalse(user.isFriendsWith(LOGGED_IN_USER));
	}
	
	@Test
	public void indicate_if_user_is_friend(){
		User user = UserBuilder.createUser()
							   .addFriends(LOGGED_IN_USER)
							   .build();
		assertTrue(user.isFriendsWith(LOGGED_IN_USER));
	}

}
