package wheelmap.org.request;

import wheelmap.org.WheelchairState;

/**
 * Constructs the Uri of a <code>/api/nodes/{node_id}</code> update/put and
 * <code>/api/nodes</code> create/post request
 */
public class NodeUpdateOrNewAllRequestBuilder extends RequestBuilder {

	private static final String RESOURCE = "nodes";
	private long id;
	private String name;
	private String type;
	private double latitude;
	private double longitude;
	private WheelchairState state;
	private String wheelchair_desc;
	private String street;
	private String housenumber;
	private String city;
	private String postcode;
	private String website;
	private String phone;

	private boolean update;

	public NodeUpdateOrNewAllRequestBuilder(final String server,
			final String apiKey, final AcceptType acceptType, long id,
			String name, String type, double latitude, double longitude,
			WheelchairState state, String wheelchair_desc, String street,
			String housenumber, String city, String postcode, String website,
			String phone, boolean update) {
		super(server, apiKey, acceptType);
		this.id = id;
		this.name = name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
		this.wheelchair_desc = wheelchair_desc;
		this.street = street;
		this.housenumber = housenumber;
		this.city = city;
		this.postcode = postcode;
		this.website = website;
		this.phone = phone;

		this.update = update;
	}

	@Override
	public String buildRequestUri() {
		final StringBuilder requestAsStringBuffer = new StringBuilder(1024);
		requestAsStringBuffer.append(String.format(baseUrl()));
		if (name != null && !(name.length() == 0)) {
			requestAsStringBuffer.append("&name=");
			requestAsStringBuffer.append(name);
		}
		if (type != null && !(type.length() == 0)) {
			requestAsStringBuffer.append("&type=");
			requestAsStringBuffer.append(type);
		}

		requestAsStringBuffer.append("&lat=");
		requestAsStringBuffer.append(latitude);
		requestAsStringBuffer.append("&lon=");
		requestAsStringBuffer.append(longitude);
		requestAsStringBuffer.append("&wheelchair=");
		requestAsStringBuffer.append(state.asRequestParameter());

		if (wheelchair_desc != null && !(wheelchair_desc.length() == 0)) {
			requestAsStringBuffer.append("&wheelchair_description=");
			requestAsStringBuffer.append(wheelchair_desc);
		}

		if (street != null && !(street.length() == 0)) {
			requestAsStringBuffer.append("&street=");
			requestAsStringBuffer.append(street);
		}

		if (housenumber != null && !(housenumber.length() == 0)) {
			requestAsStringBuffer.append("&housenumber=");
			requestAsStringBuffer.append(housenumber);
		}

		if (city != null && !(city.length() == 0)) {
			requestAsStringBuffer.append("&city=");
			requestAsStringBuffer.append(city);
		}

		if (postcode != null) {
			requestAsStringBuffer.append("&postcode=");
			requestAsStringBuffer.append(postcode);
		}

		if (website != null && !(website.length() == 0)) {
			requestAsStringBuffer.append("&website=");
			requestAsStringBuffer.append(website);
		}

		if (phone != null && !(phone.length() == 0)) {
			requestAsStringBuffer.append("&phone=");
			requestAsStringBuffer.append(phone);
		}

		return requestAsStringBuffer.toString();
	}

	@Override
	protected String resourcePath() {
		if (update)
			return RESOURCE + "/" + id;
		else
			return RESOURCE;
	}
}
