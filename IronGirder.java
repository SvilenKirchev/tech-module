import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class IronGirder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> ironGingerTime = new LinkedHashMap<>();
        Map<String, Integer> ironGingerPassengers = new LinkedHashMap<>();

        while (true) {
            String input = scanner.nextLine();

            if ("Slide rule".equals(input)) {
                break;
            }

            String[] line = input.split(":");

            String[] subLine = line[1].split("->");

            String townName = line[0];

            if (subLine[0].equals("ambush") && !ironGingerTime.containsKey(townName)) {
                continue;
            } else if (subLine[0].equals("ambush")) {
                ironGingerTime.put(townName, 0);
                ironGingerPassengers.put(townName, ironGingerPassengers.get(townName) - Integer.parseInt(subLine[1]));
                continue;
            }
            int time = Integer.parseInt(subLine[0]);
            int passengers = Integer.parseInt(subLine[1]);

            if (!ironGingerTime.containsKey(townName)) {
                ironGingerTime.put(townName, time);
                ironGingerPassengers.put(townName, passengers);
            } else {
                if (ironGingerTime.get(townName) > time || ironGingerTime.get(townName) == 0) {
                    ironGingerTime.put(townName, time);
                }
                ironGingerPassengers.put(townName, ironGingerPassengers.get(townName) + passengers);
            }

        }
        ironGingerTime.entrySet().stream().sorted(Comparator.comparingInt((ToIntFunction<Map.Entry<String, Integer>>) Map.Entry::getValue).thenComparing(Map.Entry::getKey)).forEach(e -> {
                for (Map.Entry<String, Integer> p : ironGingerPassengers.entrySet()) {
                    if (e.getValue() > 0 && e.getKey().equals(p.getKey())) {
                        System.out.println(String.format("%s -> Time: %d -> Passengers: %d", e.getKey(), e.getValue(), p.getValue()));
                        // sortirane po value na first map posle po key i printirane v format
                    }

                }
        });
    }
}
