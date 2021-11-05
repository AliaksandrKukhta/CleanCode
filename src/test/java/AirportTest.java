import aircraft.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import aircraft.MilitaryPlane;
import aircraft.PassengerPlane;
import aircraft.Plane;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AirportTest {
    private static final Logger LOGGER = LogManager.getLogger(AirportTest.class);

    private Airport airport;

    private static final List<Plane> passengerPlanes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196)
    );

    private static final List<Plane> militaryPlanes = Arrays.asList(
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 88000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT)
    );

    private static final List<Plane> experimentalPlanes = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static final List<Plane> bomberMilitaryPlanesSortedBySpeed = Arrays.asList(
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 88000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER)
    );

    private static final List<Plane> bomberMilitaryPlanesSortedByMaxLoadCapacity = Arrays.asList(
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 88000, MilitaryType.BOMBER)
    );

    private static final List<Plane> planes = Stream.of(passengerPlanes, militaryPlanes, experimentalPlanes).flatMap(Collection::stream).collect(Collectors.toList());

    private static final PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
    private static final ExperimentalPlane experimentalHighAltitudePlane = new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET);
    private static final Plane getPlaneWithMaxDistance = new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER);
    private static final Plane planeWithMaxSpeed = new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER);
    private static final Plane airbusA330Plane = new PassengerPlane("Airbus A330", 990, 14800, 80500, 222);
    private static final Plane planeWithMaxFlightDistance = new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER);

    @BeforeTest
    public void beforeTest() {
        airport = new Airport(planes);
    }

    @Test
    public void testHasModelAirbusA320() {
        LOGGER.info("TEST testBomberMilitaryPlaneWithMinSpeed started!");
        Assert.assertEquals(airport.getPlaneWithModel(), airbusA330Plane);
    }

    @Test
    public void testHasMilitaryPlane() {
        LOGGER.info("TEST testHasMilitaryPlane started!");
        Assert.assertEquals(airport.getPlaneWithModel(), airbusA330Plane);
    }

    @Test
    public void testHasPlaneWithMaxFlightDistance() {
        LOGGER.info("TEST testHasPlaneWithMaxFlightDistance started!");
        Assert.assertEquals(airport.getPlaneWithMaxFlightDistance(), planeWithMaxFlightDistance);
    }

    @Test
    public void testBomberMilitaryPlaneWithMinSpeed() {
        LOGGER.info("TEST testBomberMilitaryPlaneWithMinSpeed started!");
        Assert.assertEquals(airport.getBomberMilitaryPlanesWithMinSpeed(), bomberMilitaryPlanesSortedBySpeed.get(0));
    }

    @Test
    public void testBomberMilitaryPlaneWithMaxSpeed() {
        LOGGER.info("TEST testBomberMilitaryPlaneWithMaxSpeed started!");
        Assert.assertEquals(airport.getBomberMilitaryPlanesWithMaxSpeed(), bomberMilitaryPlanesSortedBySpeed.get(2));
    }

    @Test
    public void testBomberMilitaryPlaneWithMaxLoadCapacity() {
        LOGGER.info("TEST testBomberMilitaryPlaneWithMaxLoadCapacity started!");
        Assert.assertEquals(airport.getBomberMilitaryPlanesWithMaxLoadCapacity(), bomberMilitaryPlanesSortedByMaxLoadCapacity.get(2));
    }

    @Test
    public void testHasHighAltitudeExperimentalPlane() {
        LOGGER.info("TEST testHasHighAltitudeExperimentalPlane started!");
        Assert.assertEquals(airport.getHighAltitudeMilitaryPlanes(), experimentalHighAltitudePlane);
    }

    @Test
    public void testPassengerPlaneWithMaxCapacity() {
        LOGGER.info("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Assert.assertEquals(airport.getPassengerPlaneWithMaxPassengersCapacity(), planeWithMaxPassengerCapacity);
    }

    @Test
    public void testMaxLoadCapacityComparison() {
        LOGGER.info("TEST testMaxLoadCapacityComparison started!");
        List<Plane> sortedPlanesByMaxLoadCapacity = airport.sortByMaxLoadCapacity();
        Assert.assertEquals(sortedPlanesByMaxLoadCapacity.get(sortedPlanesByMaxLoadCapacity.size() - 1),
                (new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT)));
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        LOGGER.info("TEST testHasAtLeastOneBomberInMilitaryPlanes started!");
        Assert.assertTrue(airport.hasOneBomberPlaneInMilitary());
    }

    @Test
    public void testClassificationLevelOfExperimentalPlanes() {
        LOGGER.info("TEST testClassificationLevelOfExperimentalPlanes started!");
        Assert.assertFalse(airport.hasClassificationLevelOfExperimentalPlane());
    }

    @Test
    public void testMaxSpeedComparison() {
        LOGGER.info("TEST testMaxSpeedComparison started!");
        Assert.assertEquals(airport.getPlaneWithMaxSpeed(), planeWithMaxSpeed);
    }

    @Test
    public void testMaxDistanceComparison() {
        LOGGER.info("TEST testMaxSpeedComparison started!");
        List<Plane> sortedPlanesByMaxDistance = airport.sortByMaxDistance();
        Assert.assertEquals(airport.sortByMaxDistance().get(sortedPlanesByMaxDistance.size() - 1), getPlaneWithMaxDistance);
    }

    @Test
    public void testMaxSortedSpeedComparison() {
        LOGGER.info("TEST testMaxSpeedComparison started!");
        List<Plane> sortedPlanesByMaxSpeed = airport.sortByMaxSpeed();
        Assert.assertEquals(airport.sortByMaxSpeed().get(sortedPlanesByMaxSpeed.size() - 1), planeWithMaxSpeed);
    }
}
