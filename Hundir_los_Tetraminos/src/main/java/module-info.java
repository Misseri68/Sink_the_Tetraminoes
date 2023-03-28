module com.example.hundir_los_tetraminos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.madrid.hundir_los_tetraminos to javafx.fxml;
    exports org.madrid.hundir_los_tetraminos;
}