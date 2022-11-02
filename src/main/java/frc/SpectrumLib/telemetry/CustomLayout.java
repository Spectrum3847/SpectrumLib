package frc.SpectrumLib.telemetry;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import java.util.Map;

public class CustomLayout {
    public ShuffleboardLayout layout;

    // --------------//
    // Constructor  //
    public CustomLayout(String name, ShuffleboardTab tab) {
        layout = tab.getLayout(name, BuiltInLayouts.kGrid);
        layout.withProperties(Map.of("Label position", "TOP"));
    }

    // ---------------------//
    // initializeViewable  //
    public void initialize() {}

    // --------//
    // Update //
    public void update() {}

    protected GenericEntry quickAddPersistentWidget(
            String name, double defaultValue, int column, int row) {
        SimpleWidget widget = layout.addPersistent(name, defaultValue);
        widget.withPosition(column, row);
        return widget.getEntry();
    }

    protected GenericEntry quickAddPersistentWidget(
            String name, boolean defaultValue, int column, int row) {
        SimpleWidget widget = layout.addPersistent(name, defaultValue);
        widget.withPosition(column, row);
        return widget.getEntry();
    }

    protected GenericEntry quickAddWidget(
            String name, double defaultValue, int column, int row) {
        SimpleWidget widget = layout.add(name, defaultValue);
        widget.withPosition(column, row);
        return widget.getEntry();
    }

    protected GenericEntry quickAddWidget(
            String name, boolean defaultValue, int column, int row) {
        SimpleWidget widget = layout.add(name, defaultValue);
        widget.withPosition(column, row);
        return widget.getEntry();
    }

    protected GenericEntry addPersistentWidget(int column, int row) {
        SimpleWidget widget = layout.add("Persistent Values", true);
        widget.withPosition(column, row);
        widget.withWidget(BuiltInWidgets.kToggleSwitch);
        return widget.getEntry();
    }

    protected void setColumns(int cols) {
        layout.withProperties(Map.of("Number of columns", cols, "Label position", "TOP"));
    }

    protected void setRows(int rows) {
        layout.withProperties(Map.of("Number of rows", rows, "Label position", "TOP"));
    }

    protected void setColumnsAndRows(int cols, int rows) {
        layout.withProperties(
                Map.of("Number of columns", cols, "Number of rows", rows, "Label position", "TOP"));
    }

    protected void setSize(int width, int height) {
        layout.withSize(width, height);
    }
}
