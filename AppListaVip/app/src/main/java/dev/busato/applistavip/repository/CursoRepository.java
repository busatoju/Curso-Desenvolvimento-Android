package dev.busato.applistavip.repository;
import java.util.Arrays;
import java.util.List;

public class CursoRepository {

    public List<String> dataToSpinner() {
        return Arrays.asList(
                "Java",
                "Kotlin",
                "C#",
                "Swift",
                "Dart",
                "Flutter"
        );
    }
}
