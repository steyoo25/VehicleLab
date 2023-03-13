package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class Sample_HondaAccordian {

    @Test
    public void hondaConstructorTests() {
        HondaAccordian h = new HondaAccordian(2018);

        assertEquals(h.getFuelCapacity(), 14.5, 0.01, "Initial fuel capacity should be 14.5");
    }
}
