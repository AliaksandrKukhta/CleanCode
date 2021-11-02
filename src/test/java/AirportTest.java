import aircraft.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import aircraft.MilitaryPlane;
import aircraft.PassengerPlane;
import aircraft.Plane;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AirportTest {
    private static final Logger LOGGER = LogManager.getLogger(AirportTest.class);

    private static final List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static final PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        Assert.assertTrue(hasMilitaryPlanes());
    }

    @Test
    public void testPassengerPlaneWithMaxCapacity() {
        LOGGER.info("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Airport airport = new Airport(planes);
        Assert.assertEquals(airport.getPassengerPlaneWithMaxPassengersCapacity(), planeWithMaxPassengerCapacity);
    }

    @Test
    public void testMaxLoadCapacityComparison() {
        LOGGER.info("TEST testMaxLoadCapacityComparison started!");
        Airport airport = new Airport(planes);
        List<Plane> sortedPlane = airport.sortByMaxLoadCapacity();
        Assert.assertEquals(sortedPlane.get(sortedPlane.size() - 1), (new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT)));
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        LOGGER.info("TEST testHasAtLeastOneBomberInMilitaryPlanes started!");
        Assert.assertTrue(hasOneBomberPlaneInMilitary());
    }

    @Test
    public void testClassificationLevelOfExperimentalPlanes() {
        LOGGER.info("TEST testClassificationLevelOfExperimentalPlanes started!");
        Airport airport = new Airport(planes);
        Assert.assertTrue(airport.hasClassificationLevelOfExperimentalPlane());
    }

    public boolean hasOneBomberPlaneInMilitary() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        List<MilitaryPlane> sortedBomberMilitaryPlanes = bomberMilitaryPlanes.stream().filter(militaryPlane ->
                militaryPlane.getType() == MilitaryType.BOMBER).collect(Collectors.toList());
        return !sortedBomberMilitaryPlanes.isEmpty();
    }

    public boolean hasMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        List<MilitaryPlane> sortedTransportMilitaryPlanes = transportMilitaryPlanes.stream().filter(militaryPlane ->
                militaryPlane.getType() == MilitaryType.TRANSPORT).collect(Collectors.toList());
        return !sortedTransportMilitaryPlanes.isEmpty();
    }
}
