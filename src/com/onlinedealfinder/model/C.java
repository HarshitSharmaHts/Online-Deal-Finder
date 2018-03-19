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
		public static final String COLLECTION_NAME = "user_details";
	}
	
	public static class SESSION {
		public static final boolean EXPIRED = false;
		public static final boolean ALIVE = true;
	}
	
	public static class COOKIE {
		public static final String LOGIN_COOKIE = "login";
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
}
