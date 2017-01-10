package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

	//Keeping it for TripService_Original
	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
	public List<Trip> findTripsFor(User user){
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}	
}