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
    private List<Plane> planes;

    public List<PassengerPlane> getPassengerPlane() {
        List<Plane> allPlanes = this.planes;
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
        for (int i = 0; i < passengerPlanes.size(); i++) {
            if (passengerPlanes.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlanes.get(i);
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (int i = 0; i < militaryPlanes.size(); i++) {
            MilitaryPlane plane = militaryPlanes.get(i);
            if (plane.getType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return militaryPlanes.stream().filter(militaryPlane -> militaryPlane.getType()==MilitaryType.BOMBER).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        return experimentalPlanes.stream().filter(plane -> plane instanceof ExperimentalPlane).collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        planes.stream().sorted(Comparator.comparing(Plane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.stream().sorted(Comparator.comparing(Plane::getMaxLoadCapacity));
        return this;
    }

    public List<Plane> getPlanes() {
        return planes;
    }


    public Airport(List<Plane> planes) {
        this.planes = planes;
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