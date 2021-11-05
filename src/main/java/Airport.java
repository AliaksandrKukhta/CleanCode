import aircraft.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import aircraft.MilitaryPlane;
import aircraft.PassengerPlane;
import aircraft.Plane;

import java.util.*;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlane() {
        return planes.stream().filter(PassengerPlane.class::isInstance).map(PassengerPlane.class::cast).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes.stream().filter(MilitaryPlane.class::isInstance).map(MilitaryPlane.class::cast).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes.stream().filter(ExperimentalPlane.class::isInstance).map(ExperimentalPlane.class::cast).collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        return passengerPlanes.stream().max(Comparator.comparing(PassengerPlane::getPassengersCapacity)).get();
    }

    public Plane getPlaneWithMaxSpeed() {
        return planes.stream().max(Comparator.comparing(Plane::getMaxSpeed)).get();
    }

    public Plane getPlaneWithModel() {
        return planes.stream().filter(plane -> plane.getModel().contains("Airbus A330")).collect(Collectors.toList()).get(0);
    }

    public Plane getPlaneWithMaxFlightDistance() {
        Optional<Plane> plane = (Optional<Plane>) planes.stream().max(Comparator.comparing(Plane::getMaxFlightDistance));
        return plane.isPresent() ? plane.get() : planes.get(0);
    }

    public MilitaryPlane getBomberMilitaryPlanesWithMinSpeed() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.BOMBER).min(Comparator.comparing(Plane::getMaxSpeed)).get();
    }

    public MilitaryPlane getBomberMilitaryPlanesWithMaxSpeed() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.BOMBER).max(Comparator.comparing(Plane::getMaxSpeed)).get();
    }

    public MilitaryPlane getBomberMilitaryPlanesWithMaxLoadCapacity() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.BOMBER).max(Comparator.comparing(Plane::getMaxLoadCapacity)).get();
    }

    public ExperimentalPlane getHighAltitudeMilitaryPlanes() {
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        return experimentalPlanes.stream().filter(experimentalPlane -> experimentalPlane.getExperimentalTypes() == ExperimentalTypes.HIGH_ALTITUDE).collect(Collectors.toList()).get(0);
    }

    public List<Plane> sortByMaxLoadCapacity() {
        return planes.stream().sorted(Comparator.comparing(Plane::getMaxLoadCapacity)).collect(Collectors.toList());
    }

    public List<Plane> sortByMaxDistance() {
        return planes.stream().sorted(Comparator.comparing(Plane::getMaxFlightDistance)).collect(Collectors.toList());
    }

    public List<Plane> sortByMaxSpeed() {
        return planes.stream().sorted(Comparator.comparing(Plane::getMaxSpeed)).collect(Collectors.toList());
    }

    public boolean hasClassificationLevelOfExperimentalPlane() {
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        return experimentalPlanes.stream().allMatch(experimentalPlane -> experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED);
    }

    public boolean hasOneBomberPlaneInMilitary() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        List<MilitaryPlane> sortedBomberMilitaryPlanes = militaryPlanes.stream().filter(plane ->
                plane.getType() == MilitaryType.BOMBER).collect(Collectors.toList());
        return !sortedBomberMilitaryPlanes.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(planes, airport.planes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planes);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}