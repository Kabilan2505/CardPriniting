module com.card.printing.app.cardprinting {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens com.card.printing.app.cardprinting to javafx.fxml;
    exports com.card.printing.app.cardprinting;
    exports com.card.printing.app.cardprinting.fxcontroller;
    opens com.card.printing.app.cardprinting.fxcontroller to javafx.fxml;
}