package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {
	
	private TripDAO tripDAO;
	
	public TripService(){
		tripDAO = new TripDAO();
	}

	public List<Trip> getTripsWithFriend(User user, User loggedInUser) throws UserNotLoggedInException {
		validateLoggedInUser(loggedInUser);		
		return user.isFriendsWith(loggedInUser) 
					? getTripsForUser(user)
					: noTrips();
	}

	private void validateLoggedInUser(User loggedInUser) {
		if(loggedInUser == null){
			throw new UserNotLoggedInException();
		}
	}

	private List<Trip> noTrips() {
		return new ArrayList<>();
	}

	protected List<Trip> getTripsForUser(User user) {
		return tripDAO.findTripsFor(user);
	}
}
