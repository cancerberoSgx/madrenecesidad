package org.sgx.madrenecesidad.client.util.gmapsmissingapi;
/**
 * lists supported types for Place Searches. The following table lists types supported by the Places API when sending Place Search requests. These types cannot be used when adding a new Place.
 * @see https://developers.google.com/places/documentation/supported_types
 * @author sg
 *
 */
public class PlaceSearchTypes extends PlaceTypes {
	
	public static final String administrative_area_level_1 = "administrative_area_level_1";
	public static final String administrative_area_level_2 = "administrative_area_level_2";
	public static final String administrative_area_level_3 = "administrative_area_level_3";
	public static final String colloquial_area = "colloquial_area";
	public static final String country = "country";
	public static final String floor = "floor";
	public static final String geocode = "geocode";
	public static final String intersection = "intersection";
	public static final String locality = "locality";
	public static final String natural_feature = "natural_feature";
	public static final String neighborhood = "neighborhood";
	public static final String political = "political";
	public static final String point_of_interest = "point_of_interest";
	public static final String post_box = "post_box";
	public static final String postal_code = "postal_code";
	public static final String postal_code_prefix = "postal_code_prefix";
	public static final String postal_town = "postal_town";
	public static final String premise = "premise";
	public static final String room = "room";
	public static final String route = "route";
	public static final String street_address = "street_address";
	public static final String street_number = "street_number";
	public static final String sublocality = "sublocality";
	public static final String sublocality_level_5 = "sublocality_level_5";
	public static final String sublocality_level_4 = "sublocality_level_4";
	public static final String sublocality_level_3 = "sublocality_level_3";
	public static final String sublocality_level_2 = "sublocality_level_2";
	public static final String sublocality_level_1 = "sublocality_level_1";
	public static final String subpremise = "subpremise";
	public static final String transit_station = "transit_station";
	
	public static String[] ALL_SEARCH_PLACES = new String[]{
		administrative_area_level_1, administrative_area_level_2, administrative_area_level_3, 
		colloquial_area, country, floor, geocode, intersection, locality, natural_feature, neighborhood, 
		political, point_of_interest, post_box, postal_code, postal_code_prefix, postal_town, premise, room, 
		route, street_address, street_number, sublocality, sublocality_level_4, sublocality_level_3, sublocality_level_2,
		sublocality_level_1,sublocality_level_5, subpremise, transit_station
	}; 
}
