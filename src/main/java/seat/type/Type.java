package seat.type;

public enum Type
{
    STANDARD(1.0),
    COMFORTABLE(1.3),
    VIP(1.9);

    private final Double priceMultiplier;

    Type(double priceMultiplier)
    {
        this.priceMultiplier = priceMultiplier;
    }

    public Double getPriceMultiplier()
    {
        return priceMultiplier;
    }

}
