package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {
	
	private TripDAO tripDAO;
	
	public TripService(){
		tripDAO = new TripDAO();
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		
		validateLoggedInUser();
		
		return user.isFriendsWith(getLoggedInUser()) 
				? getTripsForUser(user)
				: noTrips();
	}

	private void validateLoggedInUser() {
		if(getLoggedInUser() == null){
			throw new UserNotLoggedInException();
		}
	}

	private List<Trip> noTrips() {
		return new ArrayList<>();
	}

	protected List<Trip> getTripsForUser(User user) {
		return tripDAO.findTripsFor(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}	
}
