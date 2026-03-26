package seedu.address.model.group;

import java.lang.reflect.Constructor;
import java.util.List;

import seedu.address.model.person.StudentId;

final class StudentIdTestUtil {
    private StudentIdTestUtil() {}

    static StudentId studentId(int seed) {
        List<String> stringCandidates = List.of(
                "A123456" + seed,
                "S" + seed,
                "STUDENT" + seed,
                String.valueOf(seed),
                "12345678"
        );

        for (Constructor<?> ctor : StudentId.class.getDeclaredConstructors()) {
            ctor.setAccessible(true);
            Class<?>[] params = ctor.getParameterTypes();
            if (params.length != 1) {
                continue;
            }

            Class<?> paramType = params[0];

            try {
                if (paramType == String.class) {
                    for (String candidate : stringCandidates) {
                        try {
                            return (StudentId) ctor.newInstance(candidate);
                        } catch (ReflectiveOperationException | IllegalArgumentException ignored) {
                            // try next candidate
                        }
                    }
                }

                if (paramType == int.class || paramType == Integer.class) {
                    return (StudentId) ctor.newInstance(seed);
                }

                if (paramType == long.class || paramType == Long.class) {
                    return (StudentId) ctor.newInstance((long) seed);
                }
            } catch (ReflectiveOperationException | IllegalArgumentException ignored) {
                // try next constructor
            }
        }

        throw new AssertionError("Unable to construct StudentId for tests. Update StudentIdTestUtil.");
    }
}
