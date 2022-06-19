module main.vychmat41 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires exp4j;

    opens main.vychmat5 to javafx.fxml;
    exports main.vychmat5;
    exports main.vychmat5.util;
    opens main.vychmat5.util to javafx.fxml;
    exports main.vychmat5.math;
    opens main.vychmat5.math to javafx.fxml;
}
