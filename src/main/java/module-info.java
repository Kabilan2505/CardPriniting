module com.card.printing.app.cardprinting {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires spring.context;
    requires org.slf4j;
    requires spring.core;
    requires spring.web;
    requires spring.beans;
    requires jdk.jfr;
    requires junrar;
    requires static lombok;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires zip4j;
    requires com.fasterxml.jackson.annotation;
    requires com.google.zxing;
    requires java.desktop;
    requires com.google.zxing.javase;
    requires opencv;
    requires java.xml.bind;
    requires com.fasterxml.jackson.databind;

    opens com.card.printing.app.cardprinting to javafx.fxml, spring.core, spring.context;
    opens com.card.printing.app.cardprinting.common to spring.core, spring.context;
    opens com.card.printing.app.cardprinting.controller to spring.core, spring.context;
    opens com.card.printing.app.cardprinting.service to spring.core, spring.context; // Open service package

    exports com.card.printing.app.cardprinting; // Export your main package
    exports com.card.printing.app.cardprinting.common to spring.beans; // Export common package for Spring beans
    exports com.card.printing.app.cardprinting.controller to spring.beans; // Export controller package for Spring beans
    exports com.card.printing.app.cardprinting.service to spring.beans, nu.pattern.OpenCV; // Export service package for Spring beans
    exports com.card.printing.app.cardprinting.dto to com.fasterxml.jackson.databind;
}
