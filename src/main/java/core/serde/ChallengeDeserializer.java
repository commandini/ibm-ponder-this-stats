package core.serde;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.model.Challenge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static core.data.Constants.JSON_EXTENSION;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ChallengeDeserializer {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public List<Challenge> deserialize(String pathToChallengesDirectory) {
        ensurePathToChallengesDirectoryIsValid(pathToChallengesDirectory);
        try {
            List<Challenge> challenges = new ArrayList<>();
            List<Path> filePaths = collectFilePaths(Path.of(pathToChallengesDirectory));
            for (Path filePath : filePaths) {
                if (filePath.getFileName().toString().endsWith(JSON_EXTENSION)) {
                    Challenge challenge = GSON.fromJson(Files.readString(filePath, UTF_8), Challenge.class);
                    challenges.add(challenge);
                }
            }
            return challenges;
        } catch (IOException e) {
            throw new SerDeException("Failed to deserialize challenges.", e);
        }
    }

    private void ensurePathToChallengesDirectoryIsValid(String pathToChallengesDirectory) {
        if (!new File(pathToChallengesDirectory).isDirectory()) {
            throw new SerDeException("Invalid directory: " + pathToChallengesDirectory);
        }
    }

    private List<Path> collectFilePaths(Path startPath) throws IOException {
        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream.filter(Files::isRegularFile).toList();
        }
    }
}
