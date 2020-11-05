package pl.morg.pracadomowatydzien4.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.morg.pracadomowatydzien4.controller.VehicleController;
import pl.morg.pracadomowatydzien4.model.Vehicle;

@Route("vehicle-view")
public class VehiclesView extends VerticalLayout {

    private VehicleController vehicleController;

    public VehiclesView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
        Grid<Vehicle> grid = new Grid<>(Vehicle.class);
        grid.setItems(vehicleController.getVehicles().getBody());
        add(grid);

    }
}
