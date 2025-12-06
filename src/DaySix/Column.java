package DaySix;

import java.math.BigInteger;

public record Column(String values) {
    public BigInteger value() {
        return new BigInteger(this.values.trim());
    }
}
