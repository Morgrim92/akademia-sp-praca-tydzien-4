package pl.morg.pracadomowatydzien4.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.morg.pracadomowatydzien4.controller.VehicleController;
import pl.morg.pracadomowatydzien4.model.Vehicle;

@Route("vehicle-add")
public class VehicleAddView extends VerticalLayout {

    private VehicleController vehicleController;

    @Autowired
    public VehicleAddView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
        TextField vehicleMark = new TextField("Mark");
        TextField vehicleModel = new TextField("Model");
        TextField vehicleColor = new TextField("Color");
        add(vehicleMark, vehicleModel, vehicleColor);

        Button addVehicle = new Button("Add");
        add(addVehicle);

        addVehicle.addClickListener(buttonClickEvent -> {
            Vehicle vehicle = new Vehicle(vehicleController.getLastID() + 1, vehicleMark.getValue(), vehicleModel.getValue(), vehicleColor.getValue());
            vehicleController.addVehicle(vehicle);
        });
    }
}
