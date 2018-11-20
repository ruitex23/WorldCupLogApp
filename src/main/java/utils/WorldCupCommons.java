package utils;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;

public class WorldCupCommons {

    private static String phaseDescription = "";

    public static String getPhaseDescription () {
        return phaseDescription;
    }

    public static void setupRadioButtonListeners(RadioButton last32RadioButton, RadioButton last16RadioButton,
                                           RadioButton quarterFinalsRadioButton, RadioButton semiFinalsRadioButton,
                                           RadioButton theFinalRadioButton, RadioButton thirdForthRadioButton) {

        last32RadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.LAST_32_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });

        last16RadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.LAST_16_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });

        quarterFinalsRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.QUARTER_FINALS_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });

        semiFinalsRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.SEMI_FINALS_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });

        thirdForthRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.THIRD_FORTH_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });

        theFinalRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    phaseDescription = WorldCupUtils.FINAL_PHASE;
                    System.out.println("Entrei!!");
                } else {
                    phaseDescription = "";
                }
            }
        });
    }

    public static void setTextFieldsOnlyNumeric(JFXTextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
