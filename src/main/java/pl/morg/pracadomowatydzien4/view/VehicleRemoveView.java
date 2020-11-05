package pl.morg.pracadomowatydzien4.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.morg.pracadomowatydzien4.controller.VehicleController;
import pl.morg.pracadomowatydzien4.model.Vehicle;

@Route("vehicle-remove")
public class VehicleRemoveView extends VerticalLayout {

    private VehicleController vehicleController;

    @Autowired
    public VehicleRemoveView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
        Grid<Vehicle> grid = new Grid<>(Vehicle.class);
        grid.setItems(vehicleController.getVehicles().getBody());
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(new NativeButtonRenderer<>("Remove", vehicle -> {
            vehicleController.removeVehicle(vehicle.getId());
            grid.getDataProvider().refreshAll();
        }));

        add(grid);
    }
}
