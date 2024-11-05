module org.example.coursework_orm {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.naming;
    requires com.jfoenix;

    requires spring.security.core;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires spring.security.crypto;

    opens org.example.coursework_orm.entity to org.hibernate.orm.core;

    opens org.example.coursework_orm.model.tm to javafx.base;

    opens org.example.coursework_orm.controller to javafx.base, javafx.controls, javafx.graphics, org.hibernate.orm.core, javafx.fxml;


    exports org.example.coursework_orm;
    exports org.example.coursework_orm.controller to javafx.fxml;
}