package seat.type;

import java.util.concurrent.ThreadLocalRandom;

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

    public static Type generateType()
    {
        double randomNumberOnTheBasisOfWhichTheSeatTypeIsDetermined = ThreadLocalRandom.current().nextDouble();

        if (randomNumberOnTheBasisOfWhichTheSeatTypeIsDetermined < 0.1)
        {
            return Type.VIP;
        }
        if (randomNumberOnTheBasisOfWhichTheSeatTypeIsDetermined < 0.4)
        {
            return Type.COMFORTABLE;
        }
        return Type.STANDARD;
    }
}
