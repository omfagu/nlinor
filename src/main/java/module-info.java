module org.nlinor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.nlinor to javafx.fxml;
    exports org.nlinor;

    opens org.nlinor.ui to javafx.graphics, javafx.fxml;
    exports org.nlinor.ui;
    exports org.nlinor.dao;

    opens org.nlinor.ui.controller to javafx.fxml;
}