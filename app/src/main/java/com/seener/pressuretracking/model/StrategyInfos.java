package com.seener.pressuretracking.model;

public class StrategyInfos {

    public enum Type {
        DOWN_UP_STAIRS("It designed that the pedestrians go upstairs and downstairs. We have carried out 20 experiments in each experimental building by each mobile phone. In these experiments, the three threshold values involved in algorithm are θ=0.01hpa,N0=N1=5\n" +
                ". In total 180 tests, the correct detection was taken by 165 times, and the floor positioning correct rate was 91.6%. Among them, Huawei Mate8, with the highest recognition accuracy, has a 95.0% floor positioning rate accuracy, and the lowest recognition accuracy is Samsung Note2 mobile phone. The floor positioning rate accuracy is 86.6%."),
        DRIVING(""),
        OUTDOOR_INDOOR(""),
        FLOOR_ESTIMATION(""),
        ;

        private String info;

        Type(String info) {

            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
}
