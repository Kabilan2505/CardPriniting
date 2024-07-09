module com.card.printing.app.cardprinting {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.card.printing.app.cardprinting to javafx.fxml;
    exports com.card.printing.app.cardprinting;
}