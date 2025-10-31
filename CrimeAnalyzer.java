package com.crimeapp;

import java.util.*;

public class CrimeAnalyzer {

    // Finds the location with the maximum number of crimes
    public static String getMostCrimeProneArea(List<CrimeRecord> records) {
        if (records == null || records.isEmpty()) {
            return "No data available.";
        }

        Map<String, Integer> areaCount = new HashMap<>();

        for (CrimeRecord r : records) {
            String area = r.getLocation().trim();
            areaCount.put(area, areaCount.getOrDefault(area, 0) + 1);
        }

        String maxArea = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> e : areaCount.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                maxArea = e.getKey();
            }
        }

        if (maxArea == null) return "No area data found.";
        return maxArea + " (Total Crimes: " + maxCount + ")";
    }
}

