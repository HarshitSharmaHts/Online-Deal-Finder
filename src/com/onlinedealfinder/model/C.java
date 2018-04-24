/**
 * 
 * @author Harshit Sharma
 * 
 */
package com.onlinedealfinder.model;

/**
 * 
 * A class to hold all the constant variables used in project.
 *
 */
public class C {

	public static class ERROR {
		public static final  String CODE_1 = "authentication_failed";
		public static final String USER_DOES_NOT_EXIST = "nhihai";
	}

	public static class SPLIT {
		public static final String PATTERN = "%%";
	}
	
	public static class MONGO {
		public static final String HOST_NAME = "localhost";
		public static final int PORT_NUMBER = 27017;
		public static final String DATABASE_NAME = "onlineDealFinderDatabase";
		public static final String USER_COLLECTION = "user_details";
		public static final String PRODUCTS_COLLECTION = "products";
	}
	
	public static class SESSION {
		public static final boolean EXPIRED = false;
		public static final boolean ALIVE = true;
	}
	
	public static class COOKIE {
		public static final String LOGIN_COOKIE = "login";
		public static final String LONGITUDE_FIELD = "longi";
		public static final String LATITUDE_FIELD = "lati";
		public static final String NAME_COOKIE = "name";
		public static final String EMAIL_COOKIE = "email";
		public static final String IMAGE_COOKIE = "image_path";
	}
	
	public static class FIELD {
		public static final String NAME = "name";
		public static final String EMAIL = "email";
		public static final String PASSWORD = "password";
		public static final String IMGURL = "imgurl";
		public static final String PHONE = "pnhno";
		public static final String DESCRIPTION = "description";
		public static final String PRICE = "price";
		public static final String TITLE = "title";
		public static final String CATEGORY = "category";
		public static final String LONGITUDE = "longitude";
		public static final String LATITUDE = "latitude";
		public static final String SOLDFLAG = "soldflag";
		public static final String CUSTOMERS= "customers";
	}
	
	public static class PASSWORD {
		public static final String SALT = "MADHAv-ODF";
	}

	public static class FILE {
		public static final  String FILE_ERROR = "file_me_error_hai";
	}

	public static class IMAGE {
		public static final String STORE_PATH = "/home/unnamed/image";
		public static final String IMAGE_FOLDER = "image";
	}

	public static class CATEGORY {
		public static final String ELECTRONICS =  "Electronics";
		public static final String TVAPPLIANCES = "TV_Appliances";
		public static final String HOMEFURNITURE = "Home_Furniture";
		public static final String SPORTS = "Sports";
		public static final String BOOK = "Books";
		public static final String OTHERS = "Others";
	}

    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public static int calculateDistance(double userLat, double userLng,
                                            double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }
}
