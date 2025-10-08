package com.crimeapp;
import java.util.*;

public class CrimeAnalyzer {

    // ---------- Predict Most Frequent Crime Type ----------
    public static String predictCrimeType(List<CrimeRecord> records) {
        if (records.isEmpty()) return "No Data";
        Map<String, Integer> map = new HashMap<>();

        for (CrimeRecord r : records) {
            map.put(r.getCrimeType(), map.getOrDefault(r.getCrimeType(), 0) + 1);
        }

        String frequent = null;
        int max = 0;
        boolean tie = false;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                frequent = entry.getKey();
                tie = false;
            } else if (entry.getValue() == max) {
                tie = true;
            }
        }

        if (tie) {
            return "No clear dominant crime type (equal frequency)";
        }

        return frequent;
    }

    // ---------- Predict Most Crime-Prone Area (Location Hotspot) ----------
    public static String predictHotspot(List<CrimeRecord> records) {
        if (records.isEmpty()) return "No Data";

        // LinkedHashMap maintains the order of entry
        Map<String, Integer> map = new LinkedHashMap<>();

        // Count the number of crimes per location
        for (CrimeRecord r : records) {
            map.put(r.getLocation(), map.getOrDefault(r.getLocation(), 0) + 1);
        }

        String hotspot = null;
        int max = 0;
        boolean tie = false;

        // Find the area with the highest number of crimes
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                hotspot = entry.getKey();
                tie = false;
            } else if (entry.getValue() == max) {
                tie = true;
            }
        }

        if (tie) {
            return "No clear hotspot (equal crimes in multiple areas)";
        }

        return hotspot;
    }

    // ---------- Optional Bonus: Top 3 Hotspots ----------
    public static String getTopHotspots(List<CrimeRecord> records) {
        if (records.isEmpty()) return "No Data";

        Map<String, Integer> map = new HashMap<>();

        for (CrimeRecord r : records) {
            map.put(r.getLocation(), map.getOrDefault(r.getLocation(), 0) + 1);
        }

        // Sort by number of crimes (descending order)
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        StringBuilder sb = new StringBuilder("Top Crime-Prone Areas:\n");
        int limit = Math.min(3, list.size());
        for (int i = 0; i < limit; i++) {
            sb.append((i + 1))
              .append(". ")
              .append(list.get(i).getKey())
              .append(" – ")
              .append(list.get(i).getValue())
              .append(" cases\n");
        }

        return sb.toString();
    }
}
