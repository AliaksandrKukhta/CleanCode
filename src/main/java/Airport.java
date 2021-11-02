import aircraft.ExperimentalPlane;
import models.ClassificationLevel;
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
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        return passengerPlanes.stream().filter(plane -> plane instanceof PassengerPlane).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        return militaryPlanes.stream().filter(plane -> plane instanceof MilitaryPlane).collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        List<PassengerPlane> sortedPassengerPlanes = passengerPlanes.stream().sorted(Comparator.comparing
                (PassengerPlane::getPassengersCapacity)).collect(Collectors.toList());
        return sortedPassengerPlanes.get(sortedPassengerPlanes.size() - 1);

    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.TRANSPORT).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.BOMBER).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        return experimentalPlanes.stream().filter(plane -> plane instanceof ExperimentalPlane).collect(Collectors.toList());
    }

    public List<Plane> sortByMaxLoadCapacity() {
        return planes.stream().sorted(Comparator.comparing(Plane::getMaxLoadCapacity)).collect(Collectors.toList());
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public boolean hasClassificationLevelOfExperimentalPlane() {
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        return experimentalPlanes.stream().allMatch(experimentalPlane -> experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED);
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