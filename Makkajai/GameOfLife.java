package com.springboot.controller;

import java.util.HashSet;
import java.util.Set;


public class GameOfLife {
    
    // Directions for the 8 neighbors (N, NE, E, SE, S, SW, W, NW)
    private static final int[][] DIRECTIONS = {
        {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, 
        {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };

    public static void main(String[] args) {
        // Test inputs
        Set<String> inputA = new HashSet<>();
        inputA.add("1,1");
        inputA.add("1,2");
        inputA.add("2,1");
        inputA.add("2,2");
        
        Set<String> inputB = new HashSet<>();
        inputB.add("0,1");
        inputB.add("1,0");
        inputB.add("2,1");
        inputB.add("0,2");
        inputB.add("1,2");

        Set<String> inputC = new HashSet<>();
        inputC.add("1,1");
        inputC.add("1,0");
        inputC.add("1,2");

        Set<String> inputD = new HashSet<>();
        inputD.add("1,1");
        inputD.add("1,2");
        inputD.add("1,3");
        inputD.add("2,2");
        inputD.add("2,3");
        inputD.add("2,4");

        // Process each pattern
        System.out.println("Output A:");
        printNextGeneration(inputA);
        
        System.out.println("\nOutput B:");
        printNextGeneration(inputB);
        
        System.out.println("\nOutput C:");
        printNextGeneration(inputC);
        
        System.out.println("\nOutput D:");
        printNextGeneration(inputD);
    }

    private static void printNextGeneration(Set<String> currentGeneration) {
        Set<String> nextGeneration = new HashSet<>();
        
        // Map to count live neighbors
        Set<String> checkedCells = new HashSet<>();
        
        for (String cell : currentGeneration) {
            int x = Integer.parseInt(cell.split(",")[0]);
            int y = Integer.parseInt(cell.split(",")[1]);
            
            // Check all neighbors
            for (int[] dir : DIRECTIONS) {
                int neighborX = x + dir[0];
                int neighborY = y + dir[1];
                String neighborKey = neighborX + "," + neighborY;
                
                // Count live neighbors
                if (currentGeneration.contains(neighborKey)) {
                    checkedCells.add(neighborKey); // Mark live cells
                } else {
                    // Count neighbors for dead cells
                    if (!checkedCells.contains(neighborKey)) {
                        checkedCells.add(neighborKey);
                        if (shouldComeToLife(currentGeneration, neighborX, neighborY)) {
                            nextGeneration.add(neighborKey);
                        }
                    }
                }
            }
            
            // Apply rules for live cells
            if (shouldSurvive(currentGeneration, x, y)) {
                nextGeneration.add(cell);
            }
        }

        // Print the next generation
        for (String cell : nextGeneration) {
            System.out.println(cell);
        }
    }

    private static boolean shouldSurvive(Set<String> currentGeneration, int x, int y) {
        int liveNeighbors = countLiveNeighbors(currentGeneration, x, y);
        
        return currentGeneration.contains(x + "," + y) && (liveNeighbors == 2 || liveNeighbors == 3);
    }

    private static boolean shouldComeToLife(Set<String> currentGeneration, int x, int y) {
        int liveNeighbors = countLiveNeighbors(currentGeneration, x, y);
        
        return !currentGeneration.contains(x + "," + y) && liveNeighbors == 3;
    }

    private static int countLiveNeighbors(Set<String> currentGeneration, int x, int y) {
        int count = 0;
        
        for (int[] dir : DIRECTIONS) {
            if (currentGeneration.contains((x + dir[0]) + "," + (y + dir[1]))) {
                count++;
            }
        }
        
        return count;
    }
}
