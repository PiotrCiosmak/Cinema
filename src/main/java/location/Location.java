package location;

public class Location
{
    public Location(String city, String postCode, String street, String houseNumber)
    {
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCity()
    {
        return city;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public String getStreet()
    {
        return street;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    @Override
    public String toString()
    {
        return city + '|' + postCode + '|' + street + '|' + houseNumber;
    }

    private String city;
    private String postCode;
    private String street;
    private String houseNumber;
}
