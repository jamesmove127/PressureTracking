package com.seener.pressuretracking.module;

public class StrategyContext {

    private IStrategy strategy;

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public Pressure.Action executeStrategy(float value) {
        return strategy.execute(value);
    }
}
