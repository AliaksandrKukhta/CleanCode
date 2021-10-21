import aircraft.ExperimentalPlane;
import models.ClassificationLevel;
import models.MilitaryType;
import aircraft.MilitaryPlane;
import aircraft.PassengerPlane;
import aircraft.Plane;

import java.util.*;

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
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (int i = 0; i < militaryPlanes.size(); i++) {
            MilitaryPlane plane = militaryPlanes.get(i);
            if (plane.getType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;

    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
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

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

    public Airport(List<Plane> planes) {
        this.planes = planes;
    }

    public boolean hasClassificationLevelOfExperimentalPlane() {
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        boolean hasUnclassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                hasUnclassifiedPlanes = true;
                break;
            }
        }
        return hasUnclassifiedPlanes;
    }
}