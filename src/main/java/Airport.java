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
        List<? extends Plane> allPlanes = this.planes;
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : allPlanes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType() == MilitaryType.BOMBER).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        return experimentalPlanes.stream().filter(plane -> plane instanceof ExperimentalPlane).collect(Collectors.toList());
    }

    public void sortByMaxDistance() {
        planes.stream().sorted(Comparator.comparing(Plane::getMaxFlightDistance)).collect(Collectors.toList());
    }

    public void sortByMaxLoadCapacity() {
        planes.stream().sorted(Comparator.comparing(Plane::getMaxLoadCapacity)).collect(Collectors.toList());
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