package clients;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {
    public static Order getWithBlackColour() {
        return new Order("Misha", "Mihailov", "Russia, Moscow", "4", "+79998887766", 3, "2020-06-06", "Now", List.of("BLACK"));
    }
    public static Order getWithGreyColour() {
        return new Order("Misha", "Mihailov", "Russia, Moscow", "4", "+79998887766", 3, "2020-06-06", "Now", List.of("GREY"));
    }
    public static Order getWithNullColour() {
        return new Order("Misha", "Mihailov", "Russia, Moscow", "4", "+79998887766", 3, "2020-06-06", "Now", List.of());
    }
    public static Order getWithTwoColour() {
        return new Order("Misha", "Mihailov", "Russia, Moscow", "4", "+79998887766", 3, "2020-06-06", "Now",List.of("GREY","BLACK"));
    }
}
