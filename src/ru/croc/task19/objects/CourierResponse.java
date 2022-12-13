package ru.croc.task19.objects;

import java.util.ArrayList;
import java.util.List;

public class CourierResponse {
    private List<Delivery> deliveries;
    private List<String> names;

    public CourierResponse(List<Delivery> deliveries, List<String> names) {
        this.deliveries = deliveries;
        this.names = names;
    }

    public List<Delivery> getDeliveries() {
        return new ArrayList<>(this.deliveries);
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<String> getNames() {
        return new ArrayList<>(this.names);
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
