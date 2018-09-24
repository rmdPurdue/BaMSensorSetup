package util;

import javafx.beans.property.SimpleStringProperty;

public class AnalogInput {

    private int inputNumber;
    private int minValue;
    private int maxValue;
    private int filterWeight;
    private SimpleStringProperty filterWeightString;

    public AnalogInput() {
    }

    public AnalogInput(int inputNumber, int minValue, int maxValue, int filterWeight) {
        this.inputNumber = inputNumber;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.filterWeight = filterWeight;
        this.filterWeightString = new SimpleStringProperty(String.valueOf(filterWeight));
    }

    public int getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(int inputNumber) {
        this.inputNumber = inputNumber;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getFilterWeight() {
        return filterWeight;
    }

    public void setFilterWeight(int filterWeight) {
        this.filterWeight = filterWeight;
        this.filterWeightString = new SimpleStringProperty(String.valueOf(filterWeight));
    }

    public String getFilterWeightString() {
        return filterWeightString.get();
    }

}
