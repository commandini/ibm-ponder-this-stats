package core.compiler;

import core.model.Challenge;
import core.model.CorrectSubmission;

import java.util.Set;

public record CompilerResult(
        Challenge challenge, Set<CorrectSubmission> duplicateSubmissions, long runtimeInMilliseconds) {
}
