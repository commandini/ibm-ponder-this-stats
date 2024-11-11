package core.serde;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.model.Challenge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static core.data.Constants.JSON_EXTENSION;

public class ChallengeSerializer {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void serialize(List<Challenge> challenges, String pathToChallengesDirectory) throws IOException {
        File challengesDirectory = new File(pathToChallengesDirectory);
        challengesDirectory.mkdir();
        Path challengesDirectoryPath = challengesDirectory.toPath();
        for (Challenge challenge : challenges) {
            Path yearDirectoryPath = challengesDirectoryPath.resolve(challenge.getYear() + "");
            Files.createDirectories(yearDirectoryPath);
            File yearDirectory = yearDirectoryPath.toFile();
            serialize(challenge, yearDirectory);
        }
    }

    private void serialize(Challenge challenge, File directory) throws IOException {
        String filePath = Path.of(directory.getPath(), challenge + JSON_EXTENSION).toString();
        try (Writer writer = new FileWriter(filePath)) {
            GSON.toJson(challenge, writer);
        }
    }
}
