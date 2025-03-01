package com.learn.challenges.designpattern.behavioural;

public class StrategyPatternExample2 {

    static interface RouteStrategy {
        void buildRoute(String start, String end);
    }

    static class DrivingRouteStrategy implements RouteStrategy {
        @Override
        public void buildRoute(String start, String end) {
            System.out.println("Building route for car driving");
        }
    }

    static class WalkingRouteStrategy implements RouteStrategy {
        @Override
        public void buildRoute(String start, String end) {
            System.out.println("Building route for walking");
        }
    }

    static class Navigator {

        RouteStrategy routeStrategy;

        public Navigator(RouteStrategy routeStrategy) {
            this.routeStrategy = routeStrategy;
        }

        void buildRoute(String start, String end) {
            this.routeStrategy.buildRoute(start, end);
        }

    }

}
