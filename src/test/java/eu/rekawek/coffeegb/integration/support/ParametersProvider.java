package eu.rekawek.coffeegb.integration.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ParametersProvider {

    private ParametersProvider() {
    }

    public static Collection<Object[]> getParameters(String dirName) throws IOException {
        return getParameters(dirName, Collections.emptyList(), 1);
    }

    public static Collection<Object[]> getParameters(String dirName, List<String> excludes, int maxDepth) throws IOException {
        Path dir = Paths.get("src/test/resources/roms", dirName);
        return Files.walk(dir, maxDepth)
                .filter(Files::isRegularFile)
                .filter(f -> f.toString().endsWith(".gb"))
                .filter(f -> !excludes.stream().anyMatch(p -> f.toString().endsWith(p)))
                .map(p -> new Object[] { dir.relativize(p).toString(), p })
                .collect(Collectors.toList());
    }

}
