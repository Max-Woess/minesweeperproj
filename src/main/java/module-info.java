module com.example.minesweeperproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.minesweeperproj to javafx.fxml;
    exports com.example.minesweeperproj;
}