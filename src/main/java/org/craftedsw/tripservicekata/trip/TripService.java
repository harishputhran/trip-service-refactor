package org.craftedsw.tripservicekata.trip;

import static java.util.Collections.emptyList;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserDetailsUnavailableException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {
	
	private TripDAO tripDAO;
	
	public TripService(){
		tripDAO = new TripDAO();
	}

	public List<Trip> getTripsWithFriend(User user, User loggedInUser) 
								throws UserDetailsUnavailableException {
		validateLoggedInUser(loggedInUser);		
		return user.isFriendsWith(loggedInUser) 
					? getTripsForUser(user)
					: noTrips();
	}

	private void validateLoggedInUser(User loggedInUser) {
		if(loggedInUser == null){
			throw new UserDetailsUnavailableException
						("Logged In User details unavailable.");
		}
	}

	private List<Trip> noTrips() {
		return emptyList();
	}

	protected List<Trip> getTripsForUser(User user) {
		return tripDAO.findTripsFor(user);
	}
}
