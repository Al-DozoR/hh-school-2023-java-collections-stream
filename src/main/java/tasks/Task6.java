package tasks;

import common.Area;
import common.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    return persons.stream()
        .flatMap(person -> personAreaIds.getOrDefault(person.getId(), Collections.emptySet()).stream()
            .flatMap(regionId -> areas.stream()
                .filter(area -> Objects.equals(area.getId(), regionId))
                .map(area -> person.getFirstName() + " - " + area.getName())))
        .collect(Collectors.toSet());
  }
}
